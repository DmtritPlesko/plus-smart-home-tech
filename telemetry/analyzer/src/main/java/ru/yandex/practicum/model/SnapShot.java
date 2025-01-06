package ru.yandex.practicum.model;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import ru.yandex.practicum.enums.ActionType;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SnapShot {

    Long id;

    Long hubId;

    List<Long> sensorId;

    ActionType conditionType;
}
