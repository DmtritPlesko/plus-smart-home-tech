package ru.yandex.practicum.service;

import ru.yandex.practicum.dto.delivery.DeliveryDto;
import ru.yandex.practicum.dto.order.OrderDto;

public interface DeliveryService {

    DeliveryDto newDelivery(DeliveryDto deliveryDto);

    void deliveryCompile(String deliveryId);

    void picked(String deliveryId);

    void failed(String deliveryId);

    Double calculateDeliveryCost(OrderDto orderDto);
}
