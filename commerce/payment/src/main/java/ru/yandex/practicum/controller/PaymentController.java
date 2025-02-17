package ru.yandex.practicum.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.dto.payment.PaymentDto;
import ru.yandex.practicum.service.PaymentService;

@RestController
@RequestMapping(path = "/api/v1/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentController {

    final PaymentService service;

    @PostMapping
    public PaymentDto paymentOrder(@RequestBody OrderDto orderDto) {
        return service.paymentOrder(orderDto);
    }

    @PostMapping(path = "/totalCost")
    public Double totalCost(@RequestBody OrderDto orderDto) {
        return service.totalCost(orderDto);
    }

    @PostMapping(path = "/refund")
    public void refund(@RequestBody String paymentId) {
        service.refund(paymentId);
    }

    @PostMapping(path = "/productCost")
    public Double productCost(@RequestBody OrderDto orderDto) {

        return service.productCost(orderDto);
    }

    @PostMapping(path = "/failed")
    public void failedPayment(@RequestBody String paymentId) {
        service.failedPayment(paymentId);
    }


}
