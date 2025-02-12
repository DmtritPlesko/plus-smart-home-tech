package ru.yandex.practicum.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.enums.QuantityState;
import ru.yandex.practicum.model.Product;

import java.util.List;

@Repository
public interface ShoppingStoreRepository extends JpaRepository<Product, String> {
    @Transactional
    @Query("UPDATE Product p SET p.quantityState = :newQuantityState WHERE p.id = :productId")
    void updateProductQuantity(@Param("productId") String productId,
                               @Param("newQuantityState") QuantityState newQuantityState);

    List<Product> findByProductCategory(String productCategory, Pageable pageable);

}
