package ru.yandex.practicum.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.dto.product.ProductDto;
import ru.yandex.practicum.enums.ProductCategory;
import ru.yandex.practicum.enums.QuantityState;
import ru.yandex.practicum.exception.product.ProductNotFoundException;
import ru.yandex.practicum.mapper.ProductMapper;
import ru.yandex.practicum.model.Product;
import ru.yandex.practicum.repository.ShoppingStoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShoppingStoreServiceImpl implements ShoppingStoreService {

    final ShoppingStoreRepository repository;
    final ProductMapper mapper;

    @Override
    public List<ProductDto> getProduct(ProductCategory productCategory, Pageable pageable) {
        return repository.findByProductCategory(productCategory.name(), pageable).stream()
                .map(mapper::toProductDto)
                .toList();
    }

    @Override
    public ProductDto addProd(ProductDto productDto) {
        return mapper.toProductDto(repository.save(mapper.toProduct(productDto)));
    }

    @Override
    public ProductDto updateProd(ProductDto productDto) {
        return mapper.toProductDto(repository.save(mapper.toProduct(productDto)));
    }

    @Override
    public boolean removeByID(String productId) {
        if (repository.findById(String.valueOf(Integer.parseInt(productId))).isPresent()) {
            repository.deleteById(productId);
        }
        throw new ProductNotFoundException("Невозможно удалить продукт с id = " + productId +
                " так как такого товара не существует");
    }

    @Override
    public boolean setQuantity(String productId, QuantityState quantityState) {
        if (repository.findById(String.valueOf(Integer.parseInt(productId))).isPresent()) {
            repository.updateProductQuantity(productId, quantityState);
            return true;
        }
        return false;
    }

    @Override
    public ProductDto getProdById(String prodId) {
        Optional<Product> productOptional = repository.findById(String.valueOf(Integer.parseInt(prodId)));
        if (productOptional.isPresent()) {
            return mapper.toProductDto(productOptional.get());
        } else {
            throw new ProductNotFoundException("Продукт с id = " + prodId + " не найден");
        }
    }
}
