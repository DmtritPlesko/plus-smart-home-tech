package ru.practicum.action;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.practicum.enums.DeviceActionType;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeviceAction {
    String sensorId;
    DeviceActionType type;
    Integer value;
}
