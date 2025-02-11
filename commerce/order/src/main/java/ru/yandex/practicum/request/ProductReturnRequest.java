package ru.yandex.practicum.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashMap;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductReturnRequest {
    String orderId;

    LinkedHashMap<String,Long> products;
}
