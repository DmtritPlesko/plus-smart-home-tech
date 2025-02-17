package ru.yandex.practicum.mapper;

import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.model.Order;

public interface OrderMapper {
    OrderDto toOrderDto(Order order);

    Order toOrder(OrderDto orderDto);

}
