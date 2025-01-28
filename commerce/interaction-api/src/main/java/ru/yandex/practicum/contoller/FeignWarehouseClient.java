package ru.yandex.practicum.contoller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.shopping.BookedProductsDto;
import ru.yandex.practicum.dto.shopping.ShoppingCartDto;
import ru.yandex.practicum.dto.warehouse.AddressDto;
import ru.yandex.practicum.request.AddProductToWarehouseRequest;
import ru.yandex.practicum.request.NewProductInWarehouseRequest;

@FeignClient(name = "warehouse")
@RequestMapping(path = "/api/v1/warehouse")
public interface FeignWarehouseClient {

    @PutMapping()
    void updateProd(@RequestBody NewProductInWarehouseRequest product);

    @PostMapping(path = "/check")
    BookedProductsDto checkProd(@RequestBody ShoppingCartDto shoppingCartDto);

    @PostMapping(path = "/add")
    void addProduct(@RequestBody AddProductToWarehouseRequest addProduct);

    @GetMapping("/address")
    AddressDto getAddress();
}
