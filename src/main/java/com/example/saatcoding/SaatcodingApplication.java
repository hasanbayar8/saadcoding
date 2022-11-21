package com.example.saatcoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class SaatcodingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaatcodingApplication.class, args);
	}

}
