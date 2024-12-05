package ru.practicum.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.model.hub.HubEvent;
import ru.practicum.model.sensor.SensorEvent;
import ru.practicum.service.EventService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventController {

    final EventService service;

    @PostMapping(path = "/hubs")
    public void collectHubsEvent(@Valid @RequestBody HubEvent hubEvent) {
        service.collectHubEvent(hubEvent);
    }

    @PostMapping(path = "/sensors")
    public void collectSensorsEvent(@Valid @RequestBody SensorEvent sensorEvent) {
        service.collectSensorEvent(sensorEvent);
    }
}

