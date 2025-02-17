package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}
