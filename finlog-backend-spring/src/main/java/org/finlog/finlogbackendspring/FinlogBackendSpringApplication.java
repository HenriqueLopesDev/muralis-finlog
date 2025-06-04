package org.finlog.finlogbackendspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class FinlogBackendSpringApplication {

    private static final Logger logger = LoggerFactory.getLogger(FinlogBackendSpringApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FinlogBackendSpringApplication.class, args);
        logger.info("Backend Application started successfully on port 8080");
    }

}
