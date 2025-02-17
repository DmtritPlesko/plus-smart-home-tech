package ru.yandex.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.contoller.FeignWarehouseClient;
import ru.yandex.practicum.dto.delivery.DeliveryDto;
import ru.yandex.practicum.dto.order.OrderDto;
import ru.yandex.practicum.enums.DeliveryState;
import ru.yandex.practicum.exception.delivery.NoDeliveryFoundException;
import ru.yandex.practicum.mapper.delivery.DeliveryMapper;
import ru.yandex.practicum.repository.DeliveryRepository;

@Component
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    final DeliveryRepository repository;
    final DeliveryMapper mapper;
    final FeignWarehouseClient client;

    @Override
    public DeliveryDto newDelivery(DeliveryDto deliveryDto) {
        return mapper.toDeliveryDto(mapper.toDelivery(deliveryDto));
    }

    @Override
    public void deliveryCompile(String deliveryId) {
        if (repository.findById(deliveryId).isEmpty()) {
            throw new NoDeliveryFoundException("Не найдена доставка");
        }
        repository.updateDeliveryState(deliveryId, DeliveryState.DELIVERED.toString());
    }

    @Override
    public void picked(String deliveryId) {
        if (repository.findById(deliveryId).isEmpty()) {
            throw new NoDeliveryFoundException("Не найдена доставка для выдачи");
        }

        repository.updateDeliveryState(deliveryId, DeliveryState.IN_PROGRESS.toString());
    }

    @Override
    public void failed(String deliveryId) {
        if (repository.findById(deliveryId).isEmpty()) {
            throw new NoDeliveryFoundException("Не найдена доставка для сбоя");
        }

        repository.updateDeliveryState(deliveryId, DeliveryState.FAILED.toString());
    }

    @Override
    public Double calculateDeliveryCost(OrderDto orderDto) {

        double baseCost = 5.0;

        if (client.getAddress().equals("ADDRESS_1")) {
            baseCost *= 1;
        } else {
            baseCost *= 2;
        }

        if (orderDto.isFragile()) {
            baseCost *= 0.2;
        }

        baseCost += orderDto.getDeliveryWeight() * 0.3;

        baseCost += orderDto.getDeliveryVolume() * 0.2;

        return baseCost;
    }
}
