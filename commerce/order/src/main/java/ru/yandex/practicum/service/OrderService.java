package ru.yandex.practicum.service;

import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.request.CreateNewOrderRequest;
import ru.yandex.practicum.request.ProductReturnRequest;

public interface OrderService {

    OrderDto getOrderByUsername(String username);

    OrderDto createNewOrder(CreateNewOrderRequest request);

    OrderDto returnOrder(ProductReturnRequest request);

    OrderDto paymentOrder(String orderId);

    OrderDto errorPayment(String orderId);

    OrderDto deliveryOrder(String orderId);

    OrderDto errorDelivery(String orderId);

    OrderDto completedOrder(String orderId);

    OrderDto calculate(String orderId);

    OrderDto calculateDelivery(String orderId);

    OrderDto assemblyOrder(String orderId);

    OrderDto failedAssembly(String orderId);
}
