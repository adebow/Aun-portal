package net.anone.aun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "net.anone.aun")
public class AunApplication {

	public static void main(String[] args) {
		SpringApplication.run(AunApplication.class, args);
	}

}
