package com.hyz.safebank;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Safe Bank API",
				version = "v1.0",
				description = "Safe Bank API"
		)

)
public class SafeBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafeBankApplication.class, args);
	}

}
