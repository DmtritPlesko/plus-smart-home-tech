package ru.yandex.practicum.contoller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.dto.payment.PaymentDto;

@FeignClient(name = "delivery")
@RequestMapping(path = "/api/v1/payment")
public interface FeignPaymentClient {
    @PostMapping
    PaymentDto paymentOrder(@RequestBody OrderDto orderDto);

    @PostMapping(path = "/totalCost")
    Double totalCost(@RequestBody OrderDto orderDto);

    @PostMapping(path = "/refund")
    void refund(@RequestBody String paymentId);

    @PostMapping(path = "/productCost")
    Double productCost(@RequestBody OrderDto orderDto);

    @PostMapping(path = "/failed")
    void failedPayment(@RequestBody String paymentId);

}
