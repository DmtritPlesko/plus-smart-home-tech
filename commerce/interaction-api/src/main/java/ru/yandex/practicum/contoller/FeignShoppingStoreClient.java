package ru.yandex.practicum.contoller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.product.ProductDto;
import ru.yandex.practicum.enums.ProductCategory;
import ru.yandex.practicum.enums.QuantityState;

import java.util.List;

@FeignClient(name = "shopping-store")
@RequestMapping(path = "api/v1/shopping-store")
public interface FeignShoppingStoreClient {

    @GetMapping
    List<ProductDto> getProduct(@RequestParam ProductCategory productCategory,
                                @RequestParam int page,
                                @RequestParam int size,
                                @RequestParam String sort);

    @PutMapping
    ProductDto updateProd(@RequestBody ProductDto productDto);

    @PostMapping
    ProductDto addProd(@RequestBody ProductDto productDto);

    @PostMapping(path = "/removeProductFromStore")
    boolean removeById(@RequestBody String productId);

    @PostMapping(path = "/quantityState")
    boolean setQuantity(@RequestBody String productId, @RequestBody QuantityState quantityState);

    @GetMapping(path = "/{productID}")
    ProductDto getProdByID(@PathVariable String prodId);

}
