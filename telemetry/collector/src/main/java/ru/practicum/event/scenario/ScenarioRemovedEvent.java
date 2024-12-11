package ru.practicum.event.scenario;

import ru.practicum.enums.HubEventType;
import ru.practicum.model.scenario.ScenarioEvent;

public class ScenarioRemovedEvent extends ScenarioEvent {
    @Override
    public HubEventType getType() {
        return HubEventType.SCENARIO_REMOVED;
    }
}
