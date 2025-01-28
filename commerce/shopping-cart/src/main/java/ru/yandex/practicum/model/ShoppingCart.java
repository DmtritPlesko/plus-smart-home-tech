package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Carts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cart_id")
    String cartId;

    @Column(name = "user_name")
    String userName;

    @OneToMany
    @JoinColumn(name = "cart_id")
    List<Position> positions;

    @Column(name = "active")
    boolean active;
}
