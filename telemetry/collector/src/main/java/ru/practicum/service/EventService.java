package ru.practicum.service;

import ru.practicum.model.hub.HubEvent;
import ru.practicum.model.sensor.SensorEvent;

public interface EventService {

    <T extends SensorEvent> void collectSensorEvent(T event);

    <T extends HubEvent> void collectHubEvent(T event);
}
