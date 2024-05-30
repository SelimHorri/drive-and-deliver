package fr.carrefour.kata.application;

import fr.carrefour.kata.domain.CustomerDeliveryUsecase;
import fr.carrefour.kata.domain.persistence.entity.Delivery;
import fr.carrefour.kata.domain.persistence.entity.DeliveryMethod;
import fr.carrefour.kata.domain.persistence.repository.CustomerRepository;
import fr.carrefour.kata.domain.persistence.repository.DeliveryMethodRepository;
import fr.carrefour.kata.domain.persistence.repository.DeliveryRepository;
import fr.carrefour.kata.infrastructure.exception.BusinessException;
import fr.carrefour.kata.presentation.request.DeliveryRequest;
import fr.carrefour.kata.presentation.response.DeliveryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
class CustomerDeliveryService implements CustomerDeliveryUsecase {
	
	private final CustomerRepository customerRepository;
	private final DeliveryRepository deliveryRepository;
	private final DeliveryMethodRepository deliveryMethodRepository;
	
	@Override
	public DeliveryResponse bookDelivery(final Integer customerId, final DeliveryRequest deliveryRequest) {
		// We will assume that the chosen timeslot is valid..
		
		final var customer = this.customerRepository
				.findById(customerId)
				.orElseThrow(() -> BusinessException.ofBadRequest(
						"Customer with %d is not recognized".formatted(customerId)));
		
		final var delivery = Delivery.builder()
				.deliveryReference(generateDeliveryReference())
				.customer(customer)
				.build();
		final var createdDelivery = this.deliveryRepository.save(delivery);
		log.debug("Delivery with id: {} has been created.", createdDelivery.getId());
		
		final var deliveryMethod = DeliveryMethod.builder()
				.delivMethod(deliveryRequest.delivMethod())
				.startDate(deliveryRequest.startDate())
				.endDate(deliveryRequest.endDate())
				.delivery(createdDelivery)
				.build();
		final var createdDeliveryMethod = this.deliveryMethodRepository.save(deliveryMethod);
		log.debug("DeliveryMethod with id: {} has been created & attached with delivery ID: {}.",
				createdDeliveryMethod.getId(),
				createdDeliveryMethod.getDelivery().getId());
		
		log.info("Delivery has been booked successfully.");
		
		return new DeliveryResponse(delivery.getDeliveryReference());
	}
	
	/**
	 * TODO: Maybe extracted to a utility class
	 */
	private static String generateDeliveryReference() {
		return "REF-" + UUID.randomUUID().toString();
	}
	
}



