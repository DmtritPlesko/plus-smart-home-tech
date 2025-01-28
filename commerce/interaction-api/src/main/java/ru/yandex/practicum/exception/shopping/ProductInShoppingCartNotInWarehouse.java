package ru.yandex.practicum.exception.shopping;

public class ProductInShoppingCartNotInWarehouse extends RuntimeException {
    public ProductInShoppingCartNotInWarehouse(String message) {
        super(message);
    }
}
