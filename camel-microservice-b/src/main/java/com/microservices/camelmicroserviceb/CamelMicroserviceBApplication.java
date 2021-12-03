package com.microservices.camelmicroserviceb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelMicroserviceBApplication {

	public CamelMicroserviceBApplication() {
		System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
	}

	public static void main(String[] args) {
		SpringApplication.run(CamelMicroserviceBApplication.class, args);
	}

}
