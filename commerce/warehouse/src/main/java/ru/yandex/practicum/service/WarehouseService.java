package ru.yandex.practicum.service;

import ru.yandex.practicum.dto.shopping.BookedProductsDto;
import ru.yandex.practicum.dto.shopping.ShoppingCartDto;
import ru.yandex.practicum.dto.warehouse.AddressDto;
import ru.yandex.practicum.request.AddProductToWarehouseRequest;
import ru.yandex.practicum.request.NewProductInWarehouseRequest;

public interface WarehouseService {

    void updateProd(NewProductInWarehouseRequest product);

    BookedProductsDto checkProd(ShoppingCartDto shoppingCartDto);

    void addProduct(AddProductToWarehouseRequest addProductToWarehouseRequest);

    AddressDto getAddress();
}
