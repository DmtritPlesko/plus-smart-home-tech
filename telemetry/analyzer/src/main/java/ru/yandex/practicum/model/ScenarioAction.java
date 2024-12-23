package ru.yandex.practicum.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "scenario_actions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScenarioAction {

    @Id
    Long scenarioId;

    @Id
    String sensorId;

    @Id
    Long actionId;
}
