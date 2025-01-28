package ru.yandex.practicum.dto.shopping;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashMap;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShoppingCartDto {

    String shoppingCartId;

    @NotNull
    LinkedHashMap<String, Long> products;

    boolean active;


}
