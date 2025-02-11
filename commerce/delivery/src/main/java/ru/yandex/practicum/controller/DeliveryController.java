package ru.yandex.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.dto.delivery.DeliveryDto;
import ru.yandex.practicum.dto.order.OrderDto;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/delivery")
public class DeliveryController {

    @PutMapping
    public DeliveryDto newDelivery(@RequestBody DeliveryDto deliveryDto) {
        return new DeliveryDto();
    }

    @PostMapping(path = "/successful")
    public void deliveryCompile(@RequestBody String deliveryId) {

    }

    @PostMapping(path = "/picked")
    public void picked(@RequestBody String deliveryId) {

    }

    @PostMapping(path = "/failed")
    public void failed(@RequestBody String deliveryId) {

    }

    @PostMapping(path = "/cost")
    public Double calculateDeliveryCost(@RequestBody OrderDto orderDto) {
        return 0.0;
    }
}
