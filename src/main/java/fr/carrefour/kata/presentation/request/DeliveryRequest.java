package fr.carrefour.kata.presentation.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import fr.carrefour.kata.domain.persistence.DelivMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record DeliveryRequest(
		@NotNull(message = "Customer ID is not specified")
		@Positive(message = "Customer ID should be a positive value")
		Integer customerId,
		
		@NotNull(message = "Start date is not specified")
		@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = Shape.STRING)
		@JsonSerialize(using = LocalDateTimeSerializer.class)
		@JsonDeserialize(using = LocalDateTimeDeserializer.class)
		LocalDateTime startDate,
		
		@NotNull(message = "End date is not specified")
		@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = Shape.STRING)
		@JsonSerialize(using = LocalDateTimeSerializer.class)
		@JsonDeserialize(using = LocalDateTimeDeserializer.class)
		LocalDateTime endDate,
		
		@NotNull(message = "Delivery method is not specified")
		DelivMethod delivMethod) {}



