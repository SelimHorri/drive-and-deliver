package fr.carrefour.kata.domain.persistence.repository;

import fr.carrefour.kata.domain.persistence.entity.DeliveryMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryMethodRepository extends JpaRepository<DeliveryMethod, Integer> {}



