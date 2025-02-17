package ru.yandex.practicum.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.dto.shopping.ShoppingCartDto;
import ru.yandex.practicum.dto.warehouse.AddressDto;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateNewOrderRequest {
    ShoppingCartDto cartDto;

    AddressDto addressDto;

    String username;
}
