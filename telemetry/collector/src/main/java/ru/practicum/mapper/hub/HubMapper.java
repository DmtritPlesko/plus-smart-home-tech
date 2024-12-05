package ru.practicum.mapper.hub;


import ru.practicum.event.hub.DeviceAddedEvent;
import ru.practicum.event.hub.DeviceRemovedEvent;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;


public interface HubMapper {

    HubEventAvro toDeviceAddedEventAvro(DeviceAddedEvent deviceAddedEvent);

    HubEventAvro toDeviceRemovedEventAvro(DeviceRemovedEvent deviceRemovedEvent);

}
