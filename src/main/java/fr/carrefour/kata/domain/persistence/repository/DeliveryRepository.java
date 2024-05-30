package fr.carrefour.kata.domain.persistence.repository;

import fr.carrefour.kata.domain.persistence.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
	
	Optional<Delivery> findByDeliveryReference(String deliveryReference);
	
}



