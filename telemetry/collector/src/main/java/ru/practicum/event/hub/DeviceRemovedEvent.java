package ru.practicum.event.hub;

import ru.practicum.enums.HubEventType;
import ru.practicum.model.hub.HubEvent;

public class DeviceRemovedEvent extends HubEvent {
    @Override
    public HubEventType getType() {
        return HubEventType.DEVICE_REMOVED;
    }
}
