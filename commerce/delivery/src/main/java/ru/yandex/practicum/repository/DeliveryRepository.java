package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.yandex.practicum.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery,String> {

    @Modifying
    @Query("UPDATE Delivery d SET d.state = :newState WHERE d.deliveryId = :deliveryId")
    void updateDeliveryState(
            @Param("deliveryId") String deliveryId,
            @Param("newState") String newState
    );
}
