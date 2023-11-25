package com.medicineservices.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.support.ResourceBundleMessageSource;
import java.util.Locale;
@SpringBootApplication
public class MedicineApplication {
	public static void main(String[] args) {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("language/messages");
		messageSource.setDefaultEncoding("UTF-8");
		System.out.println(messageSource.getMessage("hello", null, Locale.forLanguageTag("kk")));
		SpringApplication.run(MedicineApplication.class, args);
	}
}
