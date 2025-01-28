package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.enums.ProductCategory;
import ru.yandex.practicum.enums.ProductState;
import ru.yandex.practicum.enums.QuantityState;

@Getter
@Setter
@Entity
@Table(name = "Products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String productId;

    @Column(name = "product_name")
    String productName;

    @Column(name = "description")
    String description;

    @Column(name = "image_src")
    String imageSrc;

    @Column(name = "quantity_state")
    QuantityState quantityState;

    @Column(name = "product_state")
    ProductState productState;

    @Column(name = "rating")
    Double rating;

    @Column(name = "product_category")
    ProductCategory productCategory;

    @Column(name = "price")
    Double price;
}
