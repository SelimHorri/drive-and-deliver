package fr.carrefour.kata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestDeliveryKataApplication {
	
	@Bean
	@ServiceConnection
	@RestartScope
	MySQLContainer<?> mysqlContainer() {
		return new MySQLContainer<>(DockerImageName.parse("mysql:8"));
	}
	
	@Bean
	@ServiceConnection(name = "openzipkin/zipkin")
	@RestartScope
	GenericContainer<?> zipkinContainer() {
		return new GenericContainer<>(DockerImageName.parse("openzipkin/zipkin:3.4")).withExposedPorts(9411);
	}
	
	public static void main(String[] args) {
		SpringApplication.from(DeliveryKataApplication::main).with(TestDeliveryKataApplication.class).run(args);
	}
	
}



