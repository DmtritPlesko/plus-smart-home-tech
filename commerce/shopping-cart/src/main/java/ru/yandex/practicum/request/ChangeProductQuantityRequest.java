package ru.yandex.practicum.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeProductQuantityRequest {

    @NotBlank
    @NotNull
    String productId;

    @NotNull
    Long newQuantity;
}
