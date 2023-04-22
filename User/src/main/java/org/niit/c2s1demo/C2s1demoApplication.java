package org.niit.c2s1demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class C2s1demoApplication {

	public static void main(String[] args) {
		SpringApplication.run(C2s1demoApplication.class, args);
	}

}
