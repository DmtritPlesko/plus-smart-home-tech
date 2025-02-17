package ru.yandex.practicum.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.enums.State;

import java.util.LinkedHashMap;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {

    @NotNull
    String orderId;

    String shoppingCartId;

    @NotNull
    LinkedHashMap<String, Long> products;

    String paymentId;

    String deliveryId;

    State state;

    Double deliveryWeight;

    Double deliveryVolume;

    boolean fragile;

    Double totalPrice;

    Double deliveryPrice;

    Double productPrice;

}
