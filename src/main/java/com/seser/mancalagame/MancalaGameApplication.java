package com.seser.mancalagame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * This class is a spring boot application class which boots application.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.seser.mancalagame.*"})
public class MancalaGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(MancalaGameApplication.class, args);
    }

}
