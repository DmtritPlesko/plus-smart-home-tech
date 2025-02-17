package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsOrder extends JpaRepository<ru.yandex.practicum.model.ProductsOrder, String> {
}
