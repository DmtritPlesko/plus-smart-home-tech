package ru.yandex.practicum.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Nullable;
import ru.yandex.practicum.enums.ProductCategory;
import ru.yandex.practicum.enums.ProductState;
import ru.yandex.practicum.enums.QuantityState;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {

    @Nullable
    String productId;

    @NotNull
    @NotBlank
    String productName;

    @NotNull
    @NotBlank
    String description;

    @Nullable
    String imageSrc;

    @NotNull
    QuantityState quantityState;

    @NotNull
    ProductState productState;

    @NotNull
    Double rating;

    @NotNull
    ProductCategory productCategory;

    @NotNull
    Double price;
}


