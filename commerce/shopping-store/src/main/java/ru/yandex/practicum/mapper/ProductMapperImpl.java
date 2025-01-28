package ru.yandex.practicum.mapper;

import ru.yandex.practicum.dto.product.ProductDto;
import ru.yandex.practicum.model.Product;


public class ProductMapperImpl implements ProductMapper {
    @Override
    public ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setProductCategory(product.getProductCategory());
        productDto.setProductName(product.getProductName());
        productDto.setProductId(product.getProductId());
        productDto.setProductState(product.getProductState());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setRating(product.getRating());
        productDto.setImageSrc(product.getImageSrc());
        productDto.setQuantityState(product.getQuantityState());

        return productDto;
    }

    @Override
    public Product toProduct(ProductDto dto) {
        Product product = new Product();

        product.setDescription(dto.getDescription());
        product.setProductName(dto.getProductName());
        product.setProductState(dto.getProductState());
        product.setProductCategory(dto.getProductCategory());
        product.setPrice(dto.getPrice());
        product.setImageSrc(dto.getImageSrc());
        product.setRating(dto.getRating());
        product.setQuantityState(dto.getQuantityState());
        product.setProductId(dto.getProductId());

        return product;
    }

}
