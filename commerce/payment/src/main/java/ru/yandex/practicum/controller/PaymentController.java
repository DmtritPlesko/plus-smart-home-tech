package ru.yandex.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.dto.payment.PaymentDto;

@RestController
@RequestMapping(path = "/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    @PostMapping
    public PaymentDto paymentOrder(@RequestBody OrderDto orderDto) {
        return new PaymentDto();
    }

    @PostMapping(path = "/totalCost")
    public Double totalCost (@RequestBody OrderDto orderDto) {
        return 0.0;
    }

    @PostMapping(path = "/refund")
    public void refund (@RequestBody String paymentId) {

    }

    @PostMapping(path = "/productCost")
    public Double productCost(@RequestBody OrderDto orderDto) {

        return 0.0;
    }

    @PostMapping(path = "/failed")
    public void failedPayment(@RequestBody String paymentId) {

    }


}
