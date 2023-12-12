package com.example.todocrud;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.SpringApplication;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Hello world!
 *
 */
@RestController
@SpringBootApplication
public class App 
{
    Logger logger = LoggerFactory.getLogger(App.class);
    
    @RequestMapping("/")
    String home() {
        logger.info("home");
        
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
