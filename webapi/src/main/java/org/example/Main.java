package org.example;

import org.example.entities.CategoryEntity;
import org.example.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        // System.out.println("Hello Java");
    }

    @Bean
    CommandLineRunner runner(CategoryRepository repository) {
        return args -> {
            CategoryEntity category = new CategoryEntity();
            category.setName("Продукти");
            category.setDescription("Для усіх людей");
            category.setImage("product.jpg");
            category.setCreationTime(LocalDateTime.now());

            // repository.save(category);
        };
    }
}