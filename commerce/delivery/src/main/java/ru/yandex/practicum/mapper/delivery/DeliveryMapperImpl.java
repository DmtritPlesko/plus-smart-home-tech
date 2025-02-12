package ru.yandex.practicum.mapper.delivery;


import org.springframework.stereotype.Component;
import ru.yandex.practicum.dto.delivery.DeliveryDto;
import ru.yandex.practicum.mapper.address.AddressMapper;
import ru.yandex.practicum.model.Delivery;

@Component
public class DeliveryMapperImpl implements DeliveryMapper {

    @Override
    public DeliveryDto toDeliveryDto(Delivery delivery) {
        return DeliveryDto.builder()
                .deliveryId(delivery.getDeliveryId())
                .deliveryState(delivery.getState())
                .fromAddress(AddressMapper.toAddressDto(delivery.getFromAddress()))
                .toAddress(AddressMapper.toAddressDto(delivery.getToAddress()))
                .orderId(delivery.getOrderId())
                .build();
    }

    @Override
    public Delivery toDelivery(DeliveryDto deliveryDto) {
        return Delivery.builder()
                .deliveryId(deliveryDto.getDeliveryId())
                .fromAddress(AddressMapper.toAddress(deliveryDto.getFromAddress()))
                .toAddress(AddressMapper.toAddress(deliveryDto.getToAddress()))
                .orderId(deliveryDto.getOrderId())
                .state(deliveryDto.getDeliveryState())
                .build();
    }
}
