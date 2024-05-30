package fr.carrefour.kata.application;

import fr.carrefour.kata.domain.CustomerDeliveryUsecase;
import fr.carrefour.kata.domain.persistence.entity.Delivery;
import fr.carrefour.kata.domain.persistence.entity.DeliveryMethod;
import fr.carrefour.kata.domain.persistence.repository.CustomerRepository;
import fr.carrefour.kata.domain.persistence.repository.DeliveryMethodRepository;
import fr.carrefour.kata.domain.persistence.repository.DeliveryRepository;
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
	public DeliveryResponse bookDelivery(final DeliveryRequest deliveryRequest) {
		
		final var customer = this.customerRepository
				.findById(deliveryRequest.customerId())
				.orElseThrow(() -> new RuntimeException("Customer with %d is not recognized"
						.formatted(deliveryRequest.customerId())));
		
		final var delivery = Delivery.builder()
				.deliveryReference(deliveryReference())
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
	private static String deliveryReference() {
		return "REF-" + UUID.randomUUID().toString();
	}
	
}



