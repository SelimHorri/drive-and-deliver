package fr.carrefour.kata.presentation;

import fr.carrefour.kata.domain.CustomerDeliveryUsecase;
import fr.carrefour.kata.infrastructure.exception.BusinessException;
import fr.carrefour.kata.presentation.request.DeliveryRequest;
import fr.carrefour.kata.presentation.response.ApiResponse;
import fr.carrefour.kata.presentation.response.DeliveryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.api-prefix}" + "/customers")
@RequiredArgsConstructor
class CustomerDeliveryController {
	
	private final CustomerDeliveryUsecase customerDeliveryUsecase;
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("{customerId}/deliveries/book")
	ApiResponse<DeliveryResponse> bookDelivery(@PathVariable Integer customerId,
											   @RequestBody @Valid DeliveryRequest deliveryRequest) {
		return new ApiResponse<>(this.customerDeliveryUsecase.bookDelivery(customerId, deliveryRequest));
	}
	
	// TODO: Better to export to a controller advice
	@ExceptionHandler
	private <T extends BusinessException> ResponseEntity<ApiResponse<ProblemDetail>> handleBusinessException(T e) {
		var problemDetail = ProblemDetail.forStatusAndDetail(e.getStatusCode(), e.getMessage());
		return ResponseEntity
				.of(problemDetail)
				.build();
	}
	
}



