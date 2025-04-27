package com.epf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Lancer l'application avec Springboot plutôt que Tomcat (gain de temps)
@SpringBootApplication(scanBasePackages = "com.epf")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
