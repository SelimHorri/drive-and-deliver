package fr.carrefour.kata.presentation.response;

import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.Objects;

public record ApiResponse<T>(Instant timestamp, @Nullable T responseBody) {
	
	public ApiResponse {
		timestamp = Objects.requireNonNullElseGet(timestamp, Instant::now);
	}
	
	public ApiResponse(T responseBody) {
		this(null, responseBody);
	}
	
}



