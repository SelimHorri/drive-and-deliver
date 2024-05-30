package fr.carrefour.kata.domain.persistence.repository;

import fr.carrefour.kata.domain.persistence.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryMethodRepository extends JpaRepository<Delivery, Integer> {}



