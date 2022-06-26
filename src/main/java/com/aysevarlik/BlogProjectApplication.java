package com.aysevarlik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude =
		{
				org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
				org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
		})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class BlogProjectApplication {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled","true");
		System.setProperty("java.awt.headless", "false"); //Disables headless
		SpringApplication.run(BlogProjectApplication.class, args);
	}

}
