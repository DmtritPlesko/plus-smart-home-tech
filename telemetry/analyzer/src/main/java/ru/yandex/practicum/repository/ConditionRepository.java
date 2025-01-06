package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.model.Conditions;
import ru.yandex.practicum.model.ScenarioCondition;

import java.util.Optional;

public interface ConditionRepository extends JpaRepository<Conditions,Long> {

}
