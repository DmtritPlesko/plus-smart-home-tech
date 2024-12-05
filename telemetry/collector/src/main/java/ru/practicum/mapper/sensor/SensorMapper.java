package ru.practicum.mapper.sensor;

import ru.practicum.event.sensor.*;
import ru.yandex.practicum.kafka.telemetry.event.*;

public interface SensorMapper {

    ClimateSensorAvro toClimateEventAvro(ClimateSensorEvent climateSensorEvent);

    LightSensorAvro toLightSensorEventAvro(LightSensorEvent lightSensorEvent);

    MotionSensorAvro toMotionSensorEventAvro(MotionSensorEvent motionSensorEvent);

    SwitchSensorAvro toSwitchSensorEventAvro(SwitchSensorEvent switchSensorEvent);

    TemperatureSensorAvro toTemperatureSensorEventAvro(TemperatureSensorEvent temperatureSensorEvent);
}
