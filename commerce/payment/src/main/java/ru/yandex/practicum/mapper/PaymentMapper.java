package ru.yandex.practicum.mapper;

import ru.yandex.practicum.dto.payment.PaymentDto;
import ru.yandex.practicum.model.Payment;

public interface PaymentMapper {

    Payment toPayment(PaymentDto paymentDto);

    PaymentDto toPaymentDto(Payment payment);
}
