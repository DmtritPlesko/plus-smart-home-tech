package ru.yandex.practicum.exception.product;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
