package fr.carrefour.kata.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class BusinessException extends RuntimeException {
	
	private final HttpStatusCode statusCode;
	private final String message;
	
	public BusinessException(HttpStatusCode statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public static BusinessException ofBadRequest(String message) {
		return new BusinessException(HttpStatus.BAD_REQUEST, message);
	}
	
}
