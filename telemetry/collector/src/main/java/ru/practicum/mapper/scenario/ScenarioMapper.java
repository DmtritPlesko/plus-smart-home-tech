package ru.practicum.mapper.scenario;

import ru.practicum.event.scenario.ScenarioAddedEvent;
import ru.practicum.event.scenario.ScenarioRemovedEvent;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;


public interface ScenarioMapper {
    HubEventAvro toHubEventAvro(ScenarioAddedEvent scenarioAddedEvent);

    HubEventAvro toHubEventAvro(ScenarioRemovedEvent scenarioRemovedEvent);

}
