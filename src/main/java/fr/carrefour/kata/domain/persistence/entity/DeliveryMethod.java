package fr.carrefour.kata.domain.persistence.entity;

import fr.carrefour.kata.domain.model.DelivMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_methods")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DeliveryMethod {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "deliv_method")
	private DelivMethod delivMethod;
	
	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	@OneToOne
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;
	
}



