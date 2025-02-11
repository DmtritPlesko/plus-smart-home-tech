package ru.yandex.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.request.CreateNewOrderRequest;
import ru.yandex.practicum.request.ProductReturnRequest;
import ru.yandex.practicum.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/order")
public class OrderController {

    final OrderService service;

    @GetMapping
    public OrderDto getOrderByUsername (@RequestParam String username) {
        return service.getOrderByUsername(username);
    }

    @PutMapping
    public OrderDto createNewOrder(CreateNewOrderRequest request) {
        return service.createNewOrder(request);
    }

    @PostMapping(path = "/return")
    public OrderDto returnOrder(@RequestBody ProductReturnRequest request) {
        return service.returnOrder(request);
    }

    @PostMapping(path = "/payment")
    public OrderDto paymentOrder (@RequestBody String orderId) {
        return service.paymentOrder(orderId);
    }

    @PostMapping(path = "/payment/failed")
    public OrderDto errorPaymentOrder(@RequestBody String orderId) {
        return service.errorPayment(orderId);
    }

    @PostMapping(path = "/delivery")
    public OrderDto deliveryOrder (@RequestBody String orderId) {
        return service.deliveryOrder(orderId);
    }

    @PostMapping(path = "/delivery/failed")
    public OrderDto errorDelivery (@RequestBody String orderId) {
        return service.errorDelivery(orderId);
    }

    @PostMapping(path = "/completed")
    public OrderDto completedOrder(@RequestBody String orderId) {
        return service.completedOrder(orderId);
    }

    @PostMapping(path = "/calculate/total")
    public OrderDto calculate (@RequestBody String orderId) {
        return service.calculate(orderId);
    }

    @PostMapping(path = "/calculate/delivery")
    public OrderDto calculateDelivery (@RequestBody String orderId) {
        return service.calculateDelivery(orderId);
    }

    @PostMapping(path = "/assembly")
    public OrderDto assemblyOrder (@RequestBody String orderId) {
        return service.assemblyOrder(orderId);
    }

    @PostMapping(path = "/assembly/failed")
    public OrderDto failedAssembly(@RequestBody String orderId) {
        return service.failedAssembly(orderId);
    }

}
