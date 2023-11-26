package com.medicineservices.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.support.ResourceBundleMessageSource;
import java.util.Locale;
@SpringBootApplication
public class MedicineApplication {
	public static void main(String[] args) {
		SpringApplication.run(MedicineApplication.class, args);
	}
}
