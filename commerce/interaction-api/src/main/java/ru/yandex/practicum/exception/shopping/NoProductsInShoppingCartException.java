package ru.yandex.practicum.exception.shopping;

public class NoProductsInShoppingCartException extends RuntimeException {
    public NoProductsInShoppingCartException(String message) {
        super(message);
    }
}
