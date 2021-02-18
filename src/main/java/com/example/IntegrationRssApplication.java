package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

@SpringBootApplication
@ImportResource("/integration/integration.xml")
public class IntegrationRssApplication {

	public static void main(String[] args) throws IOException {

		ConfigurableApplicationContext ctx = SpringApplication.run(IntegrationRssApplication.class, args);
		System.out.println("Hit Enter to terminate");
		System.in.read();
		ctx.close();
	}

}
