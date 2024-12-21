package ru.yandex.practicum.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "scenario_conditions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScenarioCondition {

    @Column(name = "scenario_id")
    Long scenarioId;

    @Column(name = "sensor_id")
    String sensorId;

    @Column(name = "condition_id")
    Long conditionId;
}
