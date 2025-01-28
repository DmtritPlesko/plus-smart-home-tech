package ru.yandex.practicum.contoller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.shopping.BookedProductsDto;
import ru.yandex.practicum.dto.shopping.ShoppingCartDto;

import java.util.LinkedHashMap;
import java.util.Map;

@FeignClient(name = "shopping-cart")
@RequestMapping(path = "api/v1/shopping-cart")
public interface FeignShoppingCartClient {

    @GetMapping
    ShoppingCartDto getShoppingCart(@RequestParam String username);

    @PutMapping
    ShoppingCartDto update(@RequestParam String username, @RequestBody Map<String, Integer> products);

    @DeleteMapping
    void deleteCart(@RequestParam String username);

    @PostMapping(path = "/remove")
    ShoppingCartDto remove(@RequestParam String username, @RequestBody Map<String, Integer> products);

    @PostMapping(path = "/change-quantity")
    ShoppingCartDto changeQuantity(@RequestParam String username,
                                   @RequestParam LinkedHashMap<String,Long> changeProductQuantityRequest);

    @PostMapping(path = "/booking")
    BookedProductsDto booking(@RequestParam String username);
}
