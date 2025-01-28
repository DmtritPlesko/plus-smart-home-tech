package ru.yandex.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.product.ProductDto;
import ru.yandex.practicum.enums.ProductCategory;
import ru.yandex.practicum.enums.QuantityState;
import ru.yandex.practicum.service.ShoppingStoreServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/shopping-store")
public class ShoppingStoreController {

    final ShoppingStoreServiceImpl service;

    @GetMapping
    public List<ProductDto> getProduct(@RequestParam ProductCategory productCategory,
                                       @RequestParam int page,
                                       @RequestParam int size,
                                       @RequestParam String sort) {
        return service.getProduct(productCategory, PageRequest.of(page, size, Sort.by(sort).ascending()));
    }

    @PutMapping
    public ProductDto updateProd(@RequestBody ProductDto productDto) {
        return service.updateProd(productDto);
    }

    @PostMapping
    public ProductDto addProd(@RequestBody ProductDto productDto) {
        return service.addProd(productDto);
    }

    @PostMapping(path = "/removeProductFromStore")
    public boolean removeById(@RequestBody String productId) {
        return service.removeByID(productId);
    }

    @PostMapping(path = "/quantityState")
    public boolean setQuantity(@RequestBody String productId, @RequestBody QuantityState quantityState) {
        return service.setQuantity(productId, quantityState);
    }

    @GetMapping(path = "/{productID}")
    public ProductDto getProdByID(@PathVariable String prodId) {
        return service.getProdById(prodId);
    }
}
