package ru.yandex.practicum.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.dto.warehouse.DimensionDto;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewProductInWarehouseRequest {

    String productId;

    boolean fragile;

    DimensionDto dimension;

    Double weight;
}
