package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String paymentId;

    @Column(name = "total_payment")
    Double totalPayment;

    @Column(name = "delivery_total")
    Double deliveryTotal;

    @Column(name = "free_total")
    Double freeTotal;

    @Column(name = "payment_state")
    String paymentState;
}
