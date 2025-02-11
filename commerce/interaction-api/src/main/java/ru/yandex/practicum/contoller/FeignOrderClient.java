package ru.yandex.practicum.contoller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.order.OrderDto;

@FeignClient(name = "delivery")
@RequestMapping(path = "api/v1/order")
public interface FeignOrderClient {
    @GetMapping
    OrderDto getOrderByUsername (@RequestParam String username);

    @PostMapping(path = "/payment")
    OrderDto paymentOrder (@RequestBody String orderId);

    @PostMapping(path = "/payment/failed")
    OrderDto errorPaymentOrder(@RequestBody String orderId);

    @PostMapping(path = "/delivery")
    OrderDto deliveryOrder (@RequestBody String orderId);

}
