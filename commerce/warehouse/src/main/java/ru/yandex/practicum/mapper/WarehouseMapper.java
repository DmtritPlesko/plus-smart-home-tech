package ru.yandex.practicum.mapper;

import ru.yandex.practicum.model.Product;
import ru.yandex.practicum.request.NewProductInWarehouseRequest;

public interface WarehouseMapper {
    Product toProduct(NewProductInWarehouseRequest request);
}
