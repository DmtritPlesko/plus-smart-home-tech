package ru.yandex.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.delivery.DeliveryDto;
import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.service.DeliveryService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/delivery")
public class DeliveryController {

    final DeliveryService service;

    @PutMapping
    public DeliveryDto newDelivery(@RequestBody DeliveryDto deliveryDto) {
        return service.newDelivery(deliveryDto);
    }

    @PostMapping(path = "/successful")
    public void deliveryCompile(@RequestBody String deliveryId) {
        service.deliveryCompile(deliveryId);
    }

    @PostMapping(path = "/picked")
    public void picked(@RequestBody String deliveryId) {
        service.picked(deliveryId);
    }

    @PostMapping(path = "/failed")
    public void failed(@RequestBody String deliveryId) {
        service.failed(deliveryId);
    }

    @PostMapping(path = "/cost")
    public Double calculateDeliveryCost(@RequestBody OrderDto orderDto) {
        return service.calculateDeliveryCost(orderDto);
    }
}
