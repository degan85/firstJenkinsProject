package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@SpringBootApplication
@RestController
public class TestSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSpringBootApplication.class, args);
	}

	@Bean
	public MappingJackson2JsonView jsonView(){
		return new MappingJackson2JsonView();
	}

	@RequestMapping("/hello")
	private String hello() {
		return "Hello World!!@@@";
	}

}
