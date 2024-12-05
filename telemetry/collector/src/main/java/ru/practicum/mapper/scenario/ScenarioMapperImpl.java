package ru.practicum.mapper.scenario;

import ru.practicum.action.DeviceAction;
import ru.practicum.event.scenario.ScenarioAddedEvent;
import ru.practicum.event.scenario.ScenarioRemovedEvent;
import ru.practicum.model.scenario.ScenarioCondition;
import ru.yandex.practicum.kafka.telemetry.event.*;

public class ScenarioMapperImpl implements ScenarioMapper {

    public static ScenarioConditionAvro toScenarioConditionAvro(ScenarioCondition scenarioCondition) {
        return ScenarioConditionAvro.newBuilder()
                .setSensorId(scenarioCondition.getSensorId())
                .setValue(scenarioCondition.getValue())
                .setOperation(TypeOperationAvro.valueOf(String.valueOf(scenarioCondition.getOperation())))
                .setType(TypeConditionsAvro.valueOf(String.valueOf(scenarioCondition.getType())))
                .build();
    }

    public static DeviceActionAvro toDeviceActionAvro(DeviceAction deviceAction) {
        return DeviceActionAvro.newBuilder()
                .setSensorId(deviceAction.getSensorId())
                .setValue(deviceAction.getValue())
                .setType(DeviceActionTypeAvro.valueOf(String.valueOf(deviceAction.getType())))
                .build();
    }

    @Override
    public HubEventAvro toHubEventAvro(ScenarioAddedEvent scenarioAddedEvent) {
        return HubEventAvro.newBuilder()
                .setHubId(scenarioAddedEvent.getHubId())
                .setTimestamp(scenarioAddedEvent.getTimestamp())
                .setPayload(ScenarioAddedEventAvro.newBuilder()
                        .setConditions(scenarioAddedEvent.getConditions().stream()
                                .map(ScenarioMapperImpl::toScenarioConditionAvro)
                                .toList())
                        .setActions(scenarioAddedEvent.getActions().stream()
                                .map(ScenarioMapperImpl::toDeviceActionAvro)
                                .toList())
                        .build())
                .build();
    }

    @Override
    public HubEventAvro toHubEventAvro(ScenarioRemovedEvent scenarioRemovedEvent) {
        return null;
    }


}
