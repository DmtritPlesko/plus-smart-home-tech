package ru.yandex.practicum.service;

import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.dto.payment.PaymentDto;

public interface PaymentService {
    PaymentDto paymentOrder(OrderDto orderDto);

    Double totalCost(OrderDto orderDto);

    void refund(String paymentId);

    Double productCost(OrderDto orderDto);

    void failedPayment(String paymentId);
}
