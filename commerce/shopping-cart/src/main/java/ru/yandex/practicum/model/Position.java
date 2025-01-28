package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "positions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "product_id")
    String productId;

    @Column(name = "quantity")
    Long quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    Long cart;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(productId, position.productId);
    }
}
