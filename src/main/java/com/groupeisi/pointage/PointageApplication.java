package com.groupeisi.pointage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class PointageApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointageApplication.class, args);
	}

}
