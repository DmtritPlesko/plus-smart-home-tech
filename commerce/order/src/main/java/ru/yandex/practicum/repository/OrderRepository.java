package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.yandex.practicum.model.Order;

public interface OrderRepository extends JpaRepository<Order,String> {

    Order findOrderByUsername(String username);

    @Modifying
    @Query("UPDATE Order o SET o.state = :newState WHERE o.orderId = :orderId")
    void updateOrderState(
            @Param("orderId") String orderId,
            @Param("newState") String newState
    );

}
