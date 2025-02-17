package ru.yandex.practicum.dto.payment;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDto {
    String paymentId;

    Double totalPayment;

    Double deliveryTotal;

    Double freeTotal;
}
