package com.example.time.demo.demo;

import com.example.time.demo.demo.config.AWSSecretConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApplication.class);
        app.addListeners(new AWSSecretConfiguration());
        app.run(args);
    }

}
