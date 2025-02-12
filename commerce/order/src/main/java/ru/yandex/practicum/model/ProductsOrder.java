package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Entity
@Table(name = "products_orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductsOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String orderId;

    @Column(name = "product_id")
    String productId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @Column(name = "quantity")
    Long quantity;
}
