package ru.yandex.practicum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ConfigurationProperties
@EnableFeignClients
public class ShoppingStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingStoreApplication.class, args);
    }
}