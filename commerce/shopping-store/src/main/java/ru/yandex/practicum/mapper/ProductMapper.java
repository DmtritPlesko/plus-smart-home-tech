package ru.yandex.practicum.mapper;

import ru.yandex.practicum.dto.product.ProductDto;
import ru.yandex.practicum.model.Product;

public interface ProductMapper {
    ProductDto toProductDto(Product product);

    Product toProduct(ProductDto productDto);

}

