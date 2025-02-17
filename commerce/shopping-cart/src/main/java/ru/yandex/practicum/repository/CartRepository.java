package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.model.ShoppingCart;

@Repository
public interface CartRepository extends JpaRepository<ShoppingCart, String> {

    @Transactional(readOnly = true)
    ShoppingCart getShoppingCartByUsernameAndActive(String username, boolean active);

    @Transactional
    @Modifying
    @Query("UPDATE ShoppingCart s SET s.active = false WHERE s.user.username = :username")
    void deactivateCart(@Param("username") String username);
}
