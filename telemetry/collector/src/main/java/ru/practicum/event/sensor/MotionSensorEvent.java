package ru.practicum.event.sensor;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.practicum.enums.SensorEventType;
import ru.practicum.model.sensor.SensorEvent;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MotionSensorEvent extends SensorEvent {

    Integer linkQuality;

    Boolean motion;

    Integer voltage;

    @Override
    public SensorEventType getType() {
        return SensorEventType.MOTION_SENSOR_EVENT;
    }
}
