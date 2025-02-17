package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id")
    String addressId;

    @Column(name = "country")
    String country;

    @Column(name = "city")
    String city;

    @Column(name = "street")
    String street;

    @Column(name = "house")
    String house;

    @Column(name = "flat")
    String flat;
}
