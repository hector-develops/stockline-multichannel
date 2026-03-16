package com.devslopsleon.orders.DevslopsOrders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@SpringBootApplication(scanBasePackages = {"com.devslopsleon.orders"})
@EntityScan(basePackages = "com.devslopsleon.orders.core.models")
@EnableJpaRepositories(basePackages = "com.devslopsleon.orders.core.repository")
public class DevslopsOrdersApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DevslopsOrdersApplication.class, args);
	}

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String pas1= "htl2026*F1";
        String newpas1 = passwordEncoder.encode(pas1);
        System.out.println("password generated: "+ newpas1);
    }
}
