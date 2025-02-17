package ru.yandex.practicum.service;

import org.springframework.data.domain.Pageable;
import ru.yandex.practicum.dto.product.ProductDto;
import ru.yandex.practicum.enums.ProductCategory;
import ru.yandex.practicum.enums.QuantityState;

import java.util.List;

public interface ShoppingStoreService {

    List<ProductDto> getProduct(ProductCategory productCategory, Pageable pageable);

    ProductDto updateProd(ProductDto productDto);

    ProductDto addProd(ProductDto productDto);

    boolean removeByID(String productId);

    boolean setQuantity(String productId, QuantityState quantityState);

    ProductDto getProdById(String prodId);

}
