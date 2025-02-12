package ru.yandex.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.model.Order;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public OrderDto toOrderDto(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .deliveryPrice(order.getDeliveryPrice())
                .deliveryVolume(order.getDeliveryVolume())
                .state(order.getState())
                .deliveryWeight(order.getDeliveryWeight())
                .fragile(order.isFragile())
                .deliveryId(order.getDeliveryId())
                .paymentId(order.getPaymentId())
                .productPrice(order.getProductPrice())
                .shoppingCartId(order.getShoppingCartId())
                .totalPrice(order.getTotalPrice())
                .paymentId(order.getPaymentId())
                .build();
    }

    @Override
    public Order toOrder(OrderDto orderDto) {
        return Order.builder()
                .orderId(orderDto.getOrderId())
                .deliveryId(orderDto.getDeliveryId())
                .deliveryPrice(orderDto.getDeliveryPrice())
                .deliveryVolume(orderDto.getDeliveryVolume())
                .deliveryWeight(orderDto.getDeliveryWeight())
                .fragile(orderDto.isFragile())
                .paymentId(orderDto.getPaymentId())
                .productPrice(orderDto.getProductPrice())
                .state(orderDto.getState())
                .shoppingCartId(orderDto.getShoppingCartId())
                .totalPrice(orderDto.getTotalPrice())
                .build();
    }
}
