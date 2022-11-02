package com.example.preassignment.careerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;


@SpringBootApplication
public class CareerserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareerserviceApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		return WebClient
				.builder()
				.baseUrl("http://localhost:8080")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

}
