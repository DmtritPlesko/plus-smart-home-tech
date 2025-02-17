package ru.yandex.practicum.dto.warehouse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressDto {

    String addressId;

    String country;

    String city;

    String street;

    String house;

    String flat;
}
