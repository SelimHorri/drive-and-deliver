package fr.carrefour.kata.domain.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deliveries")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Delivery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "delivery_reference")
	private String deliveryReference;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
}



