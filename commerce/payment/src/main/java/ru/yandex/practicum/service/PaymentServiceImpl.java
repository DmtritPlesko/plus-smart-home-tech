package ru.yandex.practicum.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.contoller.FeignDeliveryClient;
import ru.yandex.practicum.contoller.FeignShoppingStoreClient;
import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.dto.payment.PaymentDto;
import ru.yandex.practicum.enums.PaymentStatus;
import ru.yandex.practicum.exception.payment.NoPaymentFoundException;
import ru.yandex.practicum.exception.payment.NotEnoughInfoInOrderToCalculateException;
import ru.yandex.practicum.repository.PaymentRepository;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentServiceImpl implements PaymentService {

    final PaymentRepository repository;
    final FeignShoppingStoreClient shoppingStoreClient;
    final FeignDeliveryClient deliveryClient;

    @Override
    public PaymentDto paymentOrder(OrderDto orderDto) {
        return PaymentDto.builder()
                .totalPayment(totalCost(orderDto))
                .deliveryTotal(deliveryClient.calculateDeliveryCost(orderDto))
                .freeTotal(productCost(orderDto) * 0.1)
                .build();
    }

    @Override
    public Double totalCost(OrderDto orderDto) {
        Double finalCost = productCost(orderDto);

        finalCost += finalCost * 0.1;

        return deliveryClient.calculateDeliveryCost(orderDto) + finalCost;
    }

    @Override
    public void refund(String paymentId) {
        if (repository.findById(paymentId).isEmpty()) {
            throw new NoPaymentFoundException("Указанная оплата не найдена");
        }
        repository.updatePaymentState(paymentId, PaymentStatus.SUCCESS.toString());
    }

    @Override
    public Double productCost(OrderDto orderDto) {
        Double coast = 0.0;

        try {
            for (String id : orderDto.getProducts().keySet()) {
                coast += shoppingStoreClient.getProdByID(id).getPrice();
            }
        } catch (Exception e) {
            throw new NotEnoughInfoInOrderToCalculateException("Недостаточно информации в заказе для расчёта");
        }

        return coast;
    }

    @Override
    public void failedPayment(String paymentId) {
        if (repository.findById(paymentId).isEmpty()) {
            throw new NoPaymentFoundException("Указанная оплата не найдена");
        }

        repository.updatePaymentState(paymentId, PaymentStatus.FAILED.toString());
    }
}
