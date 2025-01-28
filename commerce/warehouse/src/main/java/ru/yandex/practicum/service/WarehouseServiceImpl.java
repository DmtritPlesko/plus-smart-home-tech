package ru.yandex.practicum.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.dto.shopping.BookedProductsDto;
import ru.yandex.practicum.dto.shopping.ShoppingCartDto;
import ru.yandex.practicum.dto.warehouse.AddressDto;
import ru.yandex.practicum.exception.warehouse.NoSpecifiedProductInWarehouseException;
import ru.yandex.practicum.exception.warehouse.SpecifiedProductAlreadyInWarehouseException;
import ru.yandex.practicum.mapper.WarehouseMapper;
import ru.yandex.practicum.model.Product;
import ru.yandex.practicum.repository.WarehouseRepository;
import ru.yandex.practicum.request.AddProductToWarehouseRequest;
import ru.yandex.practicum.request.NewProductInWarehouseRequest;

import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WarehouseServiceImpl implements WarehouseService {

    private static final String[] ADDRESSES =
            new String[]{"ADDRESS_1", "ADDRESS_2"};
    private static final String CURRENT_ADDRESS =
            ADDRESSES[Random.from(new SecureRandom()).nextInt(0, 1)];
    final WarehouseRepository repository;
    final WarehouseMapper mapper;

    @Override
    public void updateProd(NewProductInWarehouseRequest product) {
        if (repository.findById(product.getProductId()).isPresent()) {
            repository.save(mapper.toProduct(product));
        }
        throw new SpecifiedProductAlreadyInWarehouseException("Ошибка, " +
                "товар с таким описанием уже зарегистрирован на складе");
    }

    @Override
    public BookedProductsDto checkProd(ShoppingCartDto shoppingCartDto) {

        BookedProductsDto bookedProductsDto = new BookedProductsDto();

        List<Product> products = repository.findAllById(shoppingCartDto.getProducts().keySet());
        Iterator<Long> productQuantity = shoppingCartDto.getProducts().values().iterator();

        StringBuilder quantityProdErr = new StringBuilder();

        for (Product product : products) {
            if (Objects.equals(product.getQuantity(), productQuantity.next())) {
                bookedProductsDto.sum(product.getWeight(),
                        product.getWidth(),
                        product.getHeight(),
                        product.getDepth());
                if (!bookedProductsDto.isFragile() && product.isFragile()) {
                    bookedProductsDto.setFragile(true);
                }

            } else {
                quantityProdErr.append("товар с id = ")
                        .append(product.getProductId())
                        .append(" не находится в требуемом количестве на складе")
                        .append("\n");
            }
        }

        if (!quantityProdErr.isEmpty()) {
            throw new SpecifiedProductAlreadyInWarehouseException("Ошибка!" + quantityProdErr);
        }

        return bookedProductsDto;
    }

    @Override
    public void addProduct(AddProductToWarehouseRequest product) {
        if (repository.findById(product.getProductId()).isPresent()) {
            repository.addProductToWarehouse(product.getProductId(), product.getQuantity());
        }
        throw new NoSpecifiedProductInWarehouseException("Нет информации о товаре на складе");
    }

    @Override
    public AddressDto getAddress() {
        return null;
    }

}
