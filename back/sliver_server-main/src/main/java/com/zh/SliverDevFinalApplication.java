package com.zh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SliverDevFinalApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SliverDevFinalApplication.class, args);
        System.out.println("Spring boot is working");
    }

}
