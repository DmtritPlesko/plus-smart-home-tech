package ru.yandex.practicum.mapper.address;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.dto.warehouse.AddressDto;
import ru.yandex.practicum.model.Address;

@Component
public class AddressMapper {
    public static AddressDto toAddressDto(Address address) {
        return AddressDto.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .country(address.getCountry())
                .flat(address.getFlat())
                .house(address.getHouse())
                .build();
    }

    public static Address toAddress(AddressDto addressDto) {
        return Address.builder()
                .street(addressDto.getStreet())
                .house(addressDto.getHouse())
                .flat(addressDto.getFlat())
                .country(addressDto.getCountry())
                .build();
    }
}
