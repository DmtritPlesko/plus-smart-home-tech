package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.model.Conditions;

public interface ConditionRepository extends JpaRepository<Conditions,Long> {
}
