package com.fsaucedo.jira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AddonApplication {

    public static void main(String[] args) throws Exception {
        final SpringApplication app = new SpringApplication(AddonApplication.class);
        ConfigurableApplicationContext context = app.run(args);
    }
}
