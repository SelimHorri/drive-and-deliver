package fr.carrefour.kata.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
class HomeController {
	
	@GetMapping
	Map<String, Object> home() {
		return Map.of("home", "This home page is not secured ;)");
	}
	
}



