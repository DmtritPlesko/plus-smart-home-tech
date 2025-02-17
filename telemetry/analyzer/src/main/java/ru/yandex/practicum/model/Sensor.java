package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.kafka.telemetry.event.DeviceType;

@Setter
@Entity
@Table(name = "sensors")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "hub_id")
    Long hubId;

    private DeviceType type;

    public Sensor(String id, String hubId, DeviceType type) {
        this.id = (long) Integer.parseInt(id);
        this.hubId = (long) Integer.parseInt(hubId);
        this.type = type;
    }
}
