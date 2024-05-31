package fr.carrefour.kata.presentation.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import fr.carrefour.kata.domain.model.constant.AppConstants;
import fr.carrefour.kata.domain.model.DelivMethod;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DeliveryRequest(
		@NotNull(message = "Start date is not specified")
		@JsonFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT, shape = Shape.STRING)
		@JsonSerialize(using = LocalDateTimeSerializer.class)
		@JsonDeserialize(using = LocalDateTimeDeserializer.class)
		LocalDateTime startDate,
		
		@NotNull(message = "End date is not specified")
		@JsonFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT, shape = Shape.STRING)
		@JsonSerialize(using = LocalDateTimeSerializer.class)
		@JsonDeserialize(using = LocalDateTimeDeserializer.class)
		LocalDateTime endDate,
		
		@NotNull(message = "Delivery method is not specified")
		DelivMethod delivMethod) {}



