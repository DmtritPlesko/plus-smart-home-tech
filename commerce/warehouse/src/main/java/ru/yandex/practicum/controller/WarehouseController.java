package ru.yandex.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.shopping.BookedProductsDto;
import ru.yandex.practicum.dto.shopping.ShoppingCartDto;
import ru.yandex.practicum.dto.warehouse.AddressDto;
import ru.yandex.practicum.request.AddProductToWarehouseRequest;
import ru.yandex.practicum.request.NewProductInWarehouseRequest;
import ru.yandex.practicum.service.WarehouseService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/warehouse")
public class WarehouseController {

    final WarehouseService service;

    @PutMapping()
    public void updateProd(@RequestBody NewProductInWarehouseRequest product) {
        service.updateProd(product);
    }

    @PostMapping(path = "/check")
    public BookedProductsDto checkProd(@RequestBody ShoppingCartDto shoppingCartDto) {
        return service.checkProd(shoppingCartDto);
    }

    @PostMapping(path = "/add")
    public void addProduct(@RequestBody AddProductToWarehouseRequest addProduct) {
        service.addProduct(addProduct);
    }

    @GetMapping("/address")
    public AddressDto getAddress() {
        return new AddressDto();
    }

}
