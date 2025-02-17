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
    public void updateProduct(NewProductInWarehouseRequest product) {

        if (repository.findById(product.getProductId()).isEmpty()) {
            throw new SpecifiedProductAlreadyInWarehouseException("Ошибка, " +
                    "товар с таким описанием уже зарегистрирован на складе");
        }
        repository.save(mapper.toProduct(product));
    }

    @Override
    public BookedProductsDto checkProduct(ShoppingCartDto shoppingCartDto) {

        BookedProductsDto bookedProductsDto = new BookedProductsDto();

        List<Product> products = repository.findAllById(shoppingCartDto.getProducts().keySet());
        Iterator<Long> productQuantity = shoppingCartDto.getProducts().values().iterator();

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
                String quantityProdErr = "товар с id = " +
                        product.getProductId() +
                        " не находится в требуемом количестве на складе" +
                        "\n";
                throw new SpecifiedProductAlreadyInWarehouseException("Ошибка!" + quantityProdErr);
            }
        }

        return bookedProductsDto;
    }

    @Override
    public void addProduct(AddProductToWarehouseRequest product) {
        if (repository.findById(product.getProductId()).isEmpty()) {
            throw new NoSpecifiedProductInWarehouseException("Нет информации о товаре на складе");
        }
        repository.addProductToWarehouse(product.getProductId(), product.getQuantity());
    }

    @Override
    public AddressDto getAddress() {
        AddressDto addressDto = new AddressDto();

        addressDto.setCity(CURRENT_ADDRESS);
        addressDto.setCountry(CURRENT_ADDRESS);
        addressDto.setFlat(CURRENT_ADDRESS);
        addressDto.setStreet(CURRENT_ADDRESS);
        addressDto.setHouse(CURRENT_ADDRESS);

        return addressDto;
    }

}
