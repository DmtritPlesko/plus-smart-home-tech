package ru.yandex.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.shopping.BookedProductsDto;
import ru.yandex.practicum.dto.shopping.ShoppingCartDto;
import ru.yandex.practicum.service.ShoppingCartService;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/shopping-cart")
public class ShoppingCartController {

    final ShoppingCartService service;

    @GetMapping
    public ShoppingCartDto getShoppingCart(@RequestParam String username) {

        return service.getShoppingCart(username);
    }

    @PutMapping
    public ShoppingCartDto update(@RequestParam String username, @RequestBody Map<String, Integer> products) {

        return new ShoppingCartDto();
    }

    @DeleteMapping
    public void deleteCart(@RequestParam String username) {
        service.deleteCart(username);
    }

    @PostMapping(path = "/remove")
    public ShoppingCartDto remove(@RequestParam String username, @RequestBody LinkedHashMap<String, Integer> products) {

        return new ShoppingCartDto();
    }

    @PostMapping(path = "/change-quantity")
    public ShoppingCartDto changeQuantity(@RequestParam String username,
                                          @RequestBody LinkedHashMap<String, Integer> products) {
        return new ShoppingCartDto();
    }

    @PostMapping(path = "/booking")
    public BookedProductsDto booking(@RequestParam String username) {

        return new BookedProductsDto();
    }
}
