package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.enums.DeliveryState;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivers")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "delivery_id")
    String deliveryId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_address_id")
    Address fromAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_address_id")
    Address toAddress;

    @Column(name = "order_id")
    String orderId;

    @Column(name = "delivery_state")
    DeliveryState state;

}
