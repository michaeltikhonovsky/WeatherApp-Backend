package com.example.WeatherWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class WeatherWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherWebAppApplication.class, args);
	}
}

@RestController
class ReactController {

	@RequestMapping(value = "/")
	public String index() {
		return "index.html";
	}
}

