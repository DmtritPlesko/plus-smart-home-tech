package ru.yandex.practicum.model;

import ru.yandex.practicum.enums.ActionType;

import java.util.List;

public class SnapShot {

    Long id;

    Long hubId;

    List<Long> sensorId;

    ActionType conditionType;
}
