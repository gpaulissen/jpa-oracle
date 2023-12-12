package com.example.todocrud;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.SpringApplication;


/**
 * Hello world!
 *
 */
@RestController
@SpringBootApplication
public class App 
{
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
