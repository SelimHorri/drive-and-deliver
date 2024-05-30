package fr.carrefour.kata.domain.persistence.repository;

import fr.carrefour.kata.domain.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {}



