package ru.yandex.practicum.mapper.delivery;

import ru.yandex.practicum.dto.delivery.DeliveryDto;
import ru.yandex.practicum.model.Delivery;

public interface DeliveryMapper {

    DeliveryDto toDeliveryDto(Delivery delivery);

    Delivery toDelivery(DeliveryDto deliveryDto);
}
