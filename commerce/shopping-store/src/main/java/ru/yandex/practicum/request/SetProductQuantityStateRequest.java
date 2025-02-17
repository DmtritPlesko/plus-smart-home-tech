package ru.yandex.practicum.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.enums.QuantityState;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SetProductQuantityStateRequest {

    String productId;

    QuantityState quantityState;
}
