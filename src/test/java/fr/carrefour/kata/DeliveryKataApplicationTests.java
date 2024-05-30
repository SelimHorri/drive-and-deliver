package fr.carrefour.kata;

import fr.carrefour.kata.constant.AppConstants;
import fr.carrefour.kata.domain.persistence.DelivMethod;
import fr.carrefour.kata.presentation.request.DeliveryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = "app.is-secured=false")
@AutoConfigureWebTestClient
class DeliveryKataApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Value("${app.api-prefix}")
	private String apiPrefix;
	
	@Test
	void givenValidInput_whenBookDelivery_thenDeleveryReferenceShouldReturn() {
		final var customerId = 2;
		final var requestBody = new DeliveryRequest(
				parseDateTime("31-05-2024 18:30:00"),
				parseDateTime("31-05-2024 20:00:00"),
				DelivMethod.DELIVERY_ASAP);
		
		this.webTestClient
				.post()
				.uri(this.apiPrefix + "/customers/{customerId}/deliveries/book", customerId)
				.bodyValue(requestBody)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.jsonPath("$").value(notNullValue())
				.jsonPath("$.timestamp").value(notNullValue())
				.jsonPath("$.responseBody").value(notNullValue())
				.jsonPath("$.responseBody.deliveryReference").value(notNullValue())
				.jsonPath("$.responseBody.deliveryReference").isNotEmpty();
	}
	
	@Test
	void givenInvalidCustomerId_whenBookDelivery_thenRuntimeExceptionShouldBeThrown() {
		final var customerId = 0;
		final var requestBody = new DeliveryRequest(
				parseDateTime("31-05-2024 18:30:00"),
				parseDateTime("31-05-2024 20:00:00"),
				DelivMethod.DELIVERY_ASAP);
		
		final var expectedBodyString = """
				{
				    "type": "about:blank",
				    "title": "Bad Request",
				    "status": 400,
				    "detail": "Customer with %d is not recognized",
				    "instance": "/api/v1/customers/%d/deliveries/book"
				}
				""".formatted(customerId, customerId);
		
		this.webTestClient
				.post()
				.uri(this.apiPrefix + "/customers/{customerId}/deliveries/book", customerId)
				.bodyValue(requestBody)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus()
				.isBadRequest()
				.expectBody()
				.json(expectedBodyString);
	}
	
	private static LocalDateTime parseDateTime(String dateTime) {
		return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(AppConstants.LOCAL_DATE_TIME_FORMAT));
	}
	
}



