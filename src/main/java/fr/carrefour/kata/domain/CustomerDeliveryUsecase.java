package fr.carrefour.kata.domain;

import fr.carrefour.kata.presentation.request.DeliveryRequest;
import fr.carrefour.kata.presentation.response.DeliveryResponse;

public interface CustomerDeliveryUsecase {
	
	DeliveryResponse bookDelivery(Integer customerId, DeliveryRequest deliveryRequest);
	
}



