package com.tp.webtools.transaps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tp.webtools.transaps.configuration.JpaConfiguration;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.tp.webtools.transaps"})
public class TransAps {
	public static void main(String[] args) {
        SpringApplication.run(TransAps.class, args);
    }
}
