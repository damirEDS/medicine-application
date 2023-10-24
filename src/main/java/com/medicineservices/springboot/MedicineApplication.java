package com.medicineservices.springboot;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicineApplication {

	public static void main(String[] args) {
		Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/mydatabase", "myuser", "mypassword").load();
		flyway.migrate();
		SpringApplication.run(MedicineApplication.class, args);
	}
}
