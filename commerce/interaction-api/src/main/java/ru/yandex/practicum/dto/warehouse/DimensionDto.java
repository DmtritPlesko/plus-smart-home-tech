package ru.yandex.practicum.dto.warehouse;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NotNull
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DimensionDto {

    Double width;

    Double height;

    Double depth;
}
