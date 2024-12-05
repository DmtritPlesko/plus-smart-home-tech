package ru.practicum.event.scenario;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.practicum.action.DeviceAction;
import ru.practicum.enums.HubEventType;
import ru.practicum.model.scenario.ScenarioCondition;
import ru.practicum.model.scenario.ScenarioEvent;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScenarioAddedEvent extends ScenarioEvent {

    List<ScenarioCondition> conditions;

    List<DeviceAction> actions;

    @Override
    public HubEventType getType() {
        return HubEventType.SCENARIO_ADDED;
    }
}
