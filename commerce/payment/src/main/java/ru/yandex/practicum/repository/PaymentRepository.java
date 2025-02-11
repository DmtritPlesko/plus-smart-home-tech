package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.yandex.practicum.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment,String> {
    @Modifying
    @Query("UPDATE Payment p SET p.paymentState = :newState WHERE p.paymentId = :paymentId")
    void updatePaymentState(
            @Param("paymentId") String paymentId,
            @Param("newState") String newState
    );
}
