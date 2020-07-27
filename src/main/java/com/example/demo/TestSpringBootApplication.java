package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TestSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSpringBootApplication.class, args);
	}


	@RequestMapping("/hello")
	private String hello() {
		return "Hello World!!";
	}

}
