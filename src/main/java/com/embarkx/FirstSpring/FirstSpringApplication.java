package com.embarkx.FirstSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstSpringApplication {

	public static void main(String[] args) {
		System.out.println("Java TimeZone: "
				+ java.util.TimeZone.getDefault().getID());

		System.out.println("Java ZoneId: "
				+ java.time.ZoneId.systemDefault());

		SpringApplication.run(FirstSpringApplication.class, args);
	}

}
