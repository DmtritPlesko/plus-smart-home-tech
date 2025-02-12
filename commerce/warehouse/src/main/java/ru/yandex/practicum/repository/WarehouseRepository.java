package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.model.Product;

@Repository
public interface WarehouseRepository extends JpaRepository<Product, String> {

    @Transactional
    @Modifying
    @Query("UPDATE ProductInWarehouse p SET p.quantity = p.quantity + :quantity WHERE p.productId = :productId")
    void addProductToWarehouse(@Param("productId") String productId, @Param("quantity") Long quantity);
}
