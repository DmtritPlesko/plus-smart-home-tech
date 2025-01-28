package ru.yandex.practicum.contoller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.shopping.BookedProductsDto;
import ru.yandex.practicum.dto.shopping.ShoppingCartDto;
import ru.yandex.practicum.dto.warehouse.AddressDto;

@FeignClient(name = "warehouse")
@RequestMapping(path = "/api/v1/warehouse")
public interface FeignWarehouseClient {

    @PostMapping(path = "/check")
    BookedProductsDto checkProd(@RequestBody ShoppingCartDto shoppingCartDto);

    @GetMapping("/address")
    AddressDto getAddress();
}
