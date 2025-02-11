package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.contoller.FeignPaymentClient;
import ru.yandex.practicum.contoller.FeignShoppingCartClient;
import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.dto.payment.PaymentDto;
import ru.yandex.practicum.enums.State;
import ru.yandex.practicum.exception.NotAuthorizedUserException;
import ru.yandex.practicum.exception.order.NoOrderFoundException;
import ru.yandex.practicum.mapper.OrderMapper;
import ru.yandex.practicum.model.Order;
import ru.yandex.practicum.repository.OrderRepository;
import ru.yandex.practicum.request.CreateNewOrderRequest;
import ru.yandex.practicum.request.ProductReturnRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final OrderMapper mapper;
    final OrderRepository repository;
    final FeignShoppingCartClient shoppingCartClient;
    final FeignPaymentClient paymentClient;

    @Override
    public OrderDto getOrderByUsername(String username) {

        if(username.isEmpty()) {
         throw new NotAuthorizedUserException("Имя пользователя не должно быть пустым");
        }

        OrderDto orderDto = mapper.toOrderDto(repository.findOrderByUsername(username));
        orderDto.setProducts(shoppingCartClient.getShoppingCart(username).getProducts());

        return orderDto;
    }

    @Override
    public OrderDto createNewOrder(CreateNewOrderRequest request) {

        Order order = new Order();

        order.setShoppingCartId(request.getCartDto().getShoppingCartId());
        order.setDeliveryId(request.getAddressDto().getAddressId());

        repository.save(order);

        return mapper.toOrderDto(order);
    }

    @Override
    public OrderDto returnOrder(ProductReturnRequest request) {
        check(request.getOrderId());

        repository.updateOrderState(request.getOrderId(),State.CANCELED.toString());

        return mapper.toOrderDto(repository.findById(request.getOrderId()).get());
    }

    @Override
    public OrderDto paymentOrder(String orderId) {
        check(orderId);

        repository.updateOrderState(orderId, State.ON_PAYMENT.toString());

        return mapper.toOrderDto(repository.findById(orderId).get());
    }

    @Override
    public OrderDto errorPayment(String orderId) {
        check(orderId);

        repository.updateOrderState(orderId,State.PAYMENT_FAILED.toString());

        return mapper.toOrderDto(repository.findById(orderId).get());
    }

    @Override
    public OrderDto deliveryOrder(String orderId) {
        check(orderId);

        repository.updateOrderState(orderId,State.ON_DELIVERY.toString());

        return mapper.toOrderDto(repository.findById(orderId).get());
    }

    @Override
    public OrderDto errorDelivery(String orderId) {
        check(orderId);

        repository.updateOrderState(orderId,State.DELIVERY_FAILED.toString());

        return mapper.toOrderDto(repository.findById(orderId).get());
    }

    @Override
    public OrderDto completedOrder(String orderId) {
        check(orderId);

        repository.updateOrderState(orderId,State.COMPLETED.toString());

        return mapper.toOrderDto(repository.findById(orderId).get());
    }

    @Override
    public OrderDto calculate(String orderId) {

        Optional<Order> orderOptional = repository.findById(orderId);

        if(orderOptional.isEmpty()) {
            throw new NoOrderFoundException("Не найден заказ");
        }

        PaymentDto price = paymentClient.
                paymentOrder(mapper.toOrderDto(orderOptional.get()));

        orderOptional.get().setTotalPrice(price.getTotalPayment());

        repository.save(orderOptional.get());

        return mapper.toOrderDto(orderOptional.get());
    }

    @Override
    public OrderDto calculateDelivery(String orderId) {

        Optional<Order> orderOptional = repository.findById(orderId);

        if(orderOptional.isEmpty()) {
            throw new NoOrderFoundException("Не найден заказ");
        }

        PaymentDto price = paymentClient.
                paymentOrder(mapper.toOrderDto(orderOptional.get()));

        orderOptional.get().setDeliveryPrice(price.getDeliveryTotal());

        repository.save(orderOptional.get());

        return mapper.toOrderDto(orderOptional.get());
    }

    @Override
    public OrderDto assemblyOrder(String orderId) {

        check(orderId);

        repository.updateOrderState(orderId,State.ASSEMBLED.toString());

        return mapper.toOrderDto(repository.findById(orderId).get());
    }

    @Override
    public OrderDto failedAssembly(String orderId) {

        check(orderId);

        repository.updateOrderState(orderId,State.ASSEMBLY_FAILED.toString());

        return mapper.toOrderDto(repository.findById(orderId).get());
    }

    private void check (String orderId) {
        if(repository.findById(orderId).isEmpty()) {
            throw new NoOrderFoundException("Не найден заказ");
        }
    }
}
