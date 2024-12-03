package ru.practicum.model.scenario;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.practicum.enums.TypeConditions;
import ru.practicum.enums.TypeOperation;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScenarioCondition {

    String sensorId;

    TypeConditions type;

    TypeOperation operation;

    Integer value;
}
