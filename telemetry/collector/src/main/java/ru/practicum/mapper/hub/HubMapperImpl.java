package ru.practicum.mapper.hub;

import ru.practicum.event.hub.DeviceAddedEvent;
import ru.practicum.event.hub.DeviceRemovedEvent;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceType;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;

public class HubMapperImpl implements HubMapper {

    @Override
    public HubEventAvro toDeviceAddedEventAvro(DeviceAddedEvent deviceAddedEvent) {
        return HubEventAvro.newBuilder()
                .setHubId(deviceAddedEvent.getHubId())
                .setTimestamp(deviceAddedEvent.getTimestamp())
                .setPayload(DeviceAddedEventAvro.newBuilder()
                        .setId(deviceAddedEvent.getId())
                        .setDeviceType(DeviceType.valueOf(deviceAddedEvent.getDeviceType().toString()))
                        .build())
                .build();
    }

    @Override
    public HubEventAvro toDeviceRemovedEventAvro(DeviceRemovedEvent deviceRemovedEvent) {
        return HubEventAvro.newBuilder()
                .setId(deviceRemovedEvent.getId())
                .setTimestamp(deviceRemovedEvent.getTimestamp())
                .setHubId(deviceRemovedEvent.getHubId())
                .setPayload(DeviceRemovedEventAvro.newBuilder()
                        .setId(deviceRemovedEvent.getId())
                        .build())
                .build();
    }
}
