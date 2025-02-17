package ru.yandex.practicum.contoller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yandex.practicum.dto.delivery.DeliveryDto;
import ru.yandex.practicum.dto.order.OrderDto;

@FeignClient(name = "delivery")
@RequestMapping(path = "/api/v1/delivery")
public interface FeignDeliveryClient {

    @PutMapping
    DeliveryDto newDelivery(@RequestBody DeliveryDto deliveryDto);

    @PostMapping(path = "/successful")
    void deliveryCompile(@RequestBody String deliveryId);

    @PostMapping(path = "/picked")
    void picked(@RequestBody String deliveryId);

    @PostMapping(path = "/failed")
    void failed(@RequestBody String deliveryId);

    @PostMapping(path = "/cost")
    Double calculateDeliveryCost(@RequestBody OrderDto orderDto);
}
