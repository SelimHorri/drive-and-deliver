package fr.carrefour.kata.presentation;

import fr.carrefour.kata.domain.CustomerDeliveryUsecase;
import fr.carrefour.kata.infrastructure.exception.BusinessException;
import fr.carrefour.kata.presentation.request.DeliveryRequest;
import fr.carrefour.kata.presentation.response.ApiResponse;
import fr.carrefour.kata.presentation.response.DeliveryResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customer-Deliveries", description = "Interaction between customer & delivery")
@RestController
@RequestMapping("${app.api-prefix}" + "/customers")
@RequiredArgsConstructor
class CustomerDeliveryController {
	
	private final CustomerDeliveryUsecase customerDeliveryUsecase;
	
	@ApiResponses(value = {
			@io.swagger.v3.oas.annotations.responses.ApiResponse(
					responseCode = "200",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ApiResponse.class)),
					description = "Book a specific delivery"),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(
					responseCode = "400",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ProblemDetail.class))),
			@io.swagger.v3.oas.annotations.responses.ApiResponse(
					responseCode = "500",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ProblemDetail.class)))
	})
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("{customerId}/deliveries/book")
	ApiResponse<DeliveryResponse> bookDelivery(@PathVariable Integer customerId,
											   @RequestBody @Valid DeliveryRequest deliveryRequest) {
		return new ApiResponse<>(this.customerDeliveryUsecase.bookDelivery(customerId, deliveryRequest));
	}
	
	// TODO: Better to export to a controller advice
	@ExceptionHandler
	private <T extends BusinessException> ResponseEntity<ProblemDetail> handleBusinessException(T e) {
		var problemDetail = ProblemDetail.forStatusAndDetail(e.getStatusCode(), e.getMessage());
		return ResponseEntity
				.of(problemDetail)
				.build();
	}
	
}



