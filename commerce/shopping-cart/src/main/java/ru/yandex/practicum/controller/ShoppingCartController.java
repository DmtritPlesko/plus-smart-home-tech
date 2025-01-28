package ru.yandex.practicum.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.shopping.BookedProductsDto;
import ru.yandex.practicum.dto.shopping.ShoppingCartDto;
import ru.yandex.practicum.request.ChangeProductQuantityRequest;
import ru.yandex.practicum.service.ShoppingCartService;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping(path = "api/v1/shopping-cart")
public class ShoppingCartController {

    final ShoppingCartService service;

    @GetMapping
    public ShoppingCartDto getShoppingCart(@RequestParam String username) {

        return service.getShoppingCart(username);
    }

    @PutMapping
    public ShoppingCartDto update(@RequestParam String username, @RequestBody Map<String, Long> products) {

        return service.updateShoppingCart(username, products);
    }

    @DeleteMapping
    public void deleteCart(@RequestParam String username) {
        service.deleteCart(username);
    }

    @PostMapping(path = "/remove")
    public ShoppingCartDto remove(@RequestParam String username, @RequestBody LinkedHashMap<String, Long> products) {

        return service.remove(username, products);
    }

    @PostMapping(path = "/change-quantity")
    public ShoppingCartDto changeQuantity(@RequestParam String username,
                                          @RequestBody ChangeProductQuantityRequest products) {
        return service.changeQuantity(username, products);
    }

    @PostMapping(path = "/booking")
    public BookedProductsDto booking(@RequestParam String username) {

        return service.booking(username);
    }
}
