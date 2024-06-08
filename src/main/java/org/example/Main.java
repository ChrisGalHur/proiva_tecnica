package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class of the application, this class is the entry point of the Spring Boot application.
 * This class is responsible for starting the Spring Boot application.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@SpringBootApplication
public class Main {

    /**
     * Main method of the application, this method is the entry point of the Spring Boot application.
     * This method is responsible for starting the Spring Boot application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}