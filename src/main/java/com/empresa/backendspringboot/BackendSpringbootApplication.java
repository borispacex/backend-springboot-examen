package com.empresa.backendspringboot;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendSpringbootApplication {

    Log LOGGER = LogFactory.getLog(BackendSpringbootApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackendSpringbootApplication.class, args);
    }

}
