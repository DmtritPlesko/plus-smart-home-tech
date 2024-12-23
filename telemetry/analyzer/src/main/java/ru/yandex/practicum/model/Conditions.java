package ru.yandex.practicum.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.enums.ConditionOperation;
import ru.yandex.practicum.enums.ConditionType;

@Getter
@Setter
@Entity
@Table(name = "conditions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Conditions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    ConditionType type;

    ConditionOperation operation;

    Integer value;
}
