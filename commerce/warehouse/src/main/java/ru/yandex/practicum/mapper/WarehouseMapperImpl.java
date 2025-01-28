package ru.yandex.practicum.mapper;

import ru.yandex.practicum.model.Product;
import ru.yandex.practicum.request.NewProductInWarehouseRequest;

public class WarehouseMapperImpl implements WarehouseMapper {
    @Override
    public Product toProduct(NewProductInWarehouseRequest request) {

        Product product = new Product();

        product.setProductId(request.getProductId());
        product.setFragile(request.isFragile());
        product.setWeight(request.getWeight());
        product.setWidth(request.getDimension().getWidth());
        product.setHeight(request.getDimension().getHeight());
        product.setDepth(request.getDimension().getDepth());

        return product;
    }
}
