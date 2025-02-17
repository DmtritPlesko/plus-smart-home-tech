package ru.yandex.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.dto.product.ProductDto;
import ru.yandex.practicum.model.Product;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public ProductDto toProductDto(Product product) {

        return new ProductDto().toBuilder()
                .productId(product.getProductId())
                .productState(product.getProductState())
                .description(product.getDescription())
                .imageSrc(product.getImageSrc())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .productName(product.getProductName())
                .quantityState(product.getQuantityState())
                .rating(product.getRating())
                .build();
    }

    @Override
    public Product toProduct(ProductDto dto) {

        return new Product().toBuilder()
                .productId(dto.getProductId())
                .productState(dto.getProductState())
                .productName(dto.getProductName())
                .productCategory(dto.getProductCategory())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .imageSrc(dto.getImageSrc())
                .quantityState(dto.getQuantityState())
                .rating(dto.getRating())
                .build();
    }

}
