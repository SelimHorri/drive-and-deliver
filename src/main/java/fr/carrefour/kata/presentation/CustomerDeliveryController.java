package fr.carrefour.kata.presentation;

import fr.carrefour.kata.domain.CustomerDeliveryUsecase;
import fr.carrefour.kata.presentation.request.DeliveryRequest;
import fr.carrefour.kata.presentation.response.ApiResponse;
import fr.carrefour.kata.presentation.response.DeliveryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers/deliveries")
@RequiredArgsConstructor
class CustomerDeliveryController {
	
	private final CustomerDeliveryUsecase customerDeliveryUsecase;
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/book")
	ApiResponse<DeliveryResponse> bookDelivery(@RequestBody @Valid DeliveryRequest deliveryRequest) {
		return new ApiResponse<>(this.customerDeliveryUsecase.bookDelivery(deliveryRequest));
	}
	
}



