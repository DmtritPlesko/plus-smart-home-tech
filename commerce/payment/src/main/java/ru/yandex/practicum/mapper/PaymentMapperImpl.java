package ru.yandex.practicum.mapper;

import ru.yandex.practicum.dto.payment.PaymentDto;
import ru.yandex.practicum.model.Payment;

public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public Payment toPayment(PaymentDto paymentDto) {
        return Payment.builder()
                .totalPayment(paymentDto.getTotalPayment())
                .deliveryTotal(paymentDto.getDeliveryTotal())
                .paymentId(paymentDto.getPaymentId())
                .freeTotal(paymentDto.getFreeTotal())
                .build();
    }

    @Override
    public PaymentDto toPaymentDto(Payment payment) {
        return PaymentDto.builder()
                .totalPayment(payment.getTotalPayment())
                .deliveryTotal(payment.getDeliveryTotal())
                .freeTotal(payment.getFreeTotal())
                .paymentId(payment.getPaymentId())
                .build();
    }
}
