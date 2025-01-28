package ru.yandex.practicum.dto.shopping;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookedProductsDto {

    @NotNull
    Double deliveryWeight;

    @NotNull
    Double deliveryVolume;

    boolean fragile;

    public void sum(Double weight, Double width, Double height, Double depth) {
        this.deliveryWeight += weight;
        this.deliveryVolume += width * height * depth;
    }
}
