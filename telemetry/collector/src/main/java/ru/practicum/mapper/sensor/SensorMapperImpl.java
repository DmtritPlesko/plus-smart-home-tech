package ru.practicum.mapper.sensor;

import ru.practicum.event.sensor.*;
import ru.yandex.practicum.kafka.telemetry.event.*;

public class SensorMapperImpl implements SensorMapper {
    @Override
    public ClimateSensorAvro toClimateEventAvro(ClimateSensorEvent climateSensorEvent) {
        return ClimateSensorAvro.newBuilder()
                .setCo2Level(climateSensorEvent.getCo2Level())
                .setHumidity(climateSensorEvent.getHumidity())
                .setTemperatureC(climateSensorEvent.getTemperatureC())
                .build();
    }

    @Override
    public LightSensorAvro toLightSensorEventAvro(LightSensorEvent lightSensorEvent) {
        return LightSensorAvro.newBuilder()
                .setLinkQuality(lightSensorEvent.getLinkQuality())
                .setLuminosity(lightSensorEvent.getLuminosity())
                .build();
    }

    @Override
    public MotionSensorAvro toMotionSensorEventAvro(MotionSensorEvent motionSensorEvent) {
        return MotionSensorAvro.newBuilder()
                .setMotion(motionSensorEvent.getMotion())
                .setLinkQuality(motionSensorEvent.getLinkQuality())
                .setVoltage(motionSensorEvent.getVoltage())
                .build();
    }

    @Override
    public SwitchSensorAvro toSwitchSensorEventAvro(SwitchSensorEvent switchSensorEvent) {
        return SwitchSensorAvro.newBuilder()
                .setState(switchSensorEvent.getState())
                .build();
    }

    @Override
    public TemperatureSensorAvro toTemperatureSensorEventAvro(TemperatureSensorEvent temperatureSensorEvent) {
        return TemperatureSensorAvro.newBuilder()
                .setId(temperatureSensorEvent.getId())
                .setHubId(temperatureSensorEvent.getHubId())
                .setTemperatureC(temperatureSensorEvent.getTemperatureC())
                .setTemperatureF(temperatureSensorEvent.getTemperatureF())
                .build();
    }
}
