package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String productId;

    @Column(name = "fragile")
    boolean fragile;

    @Column(name = "width")
    Double width;

    @Column(name = "height")
    Double height;

    @Column(name = "depth")
    Double depth;

    @Column(name = "weight")
    Double weight;

    @Column(name = "quantity")
    Long quantity;
}
