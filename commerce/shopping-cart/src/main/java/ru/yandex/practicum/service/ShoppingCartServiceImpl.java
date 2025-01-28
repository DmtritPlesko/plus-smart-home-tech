package ru.yandex.practicum.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.contoller.FeignWarehouseClient;
import ru.yandex.practicum.dto.shopping.BookedProductsDto;
import ru.yandex.practicum.dto.shopping.ShoppingCartDto;
import ru.yandex.practicum.exception.shopping.NoProductsInShoppingCartException;
import ru.yandex.practicum.exception.shopping.NotAuthorizedUserException;
import ru.yandex.practicum.mapper.ShoppingCartMapper;
import ru.yandex.practicum.model.Position;
import ru.yandex.practicum.model.ShoppingCart;
import ru.yandex.practicum.repository.CartRepository;
import ru.yandex.practicum.repository.PositionRepository;
import ru.yandex.practicum.request.ChangeProductQuantityRequest;

import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShoppingCartServiceImpl implements ShoppingCartService {

    final CartRepository cartRepository;
    final PositionRepository positionRepository;
    final ShoppingCartMapper mapper;
    final FeignWarehouseClient client;

    @Override
    public ShoppingCartDto getShoppingCart(String username) {
        if (username.isEmpty()) {
            throw new NotAuthorizedUserException("Имя пользователя не должно быть пустым");
        }
        return mapper.toShoppingCartDto(cartRepository.getShoppingCartByUsernameAndActive(username, true));
    }

    @Override
    public ShoppingCartDto updateShoppingCart(String username, Map<String, Integer> products) {
        return null;
    }

    @Override
    public void deleteCart(String username) {
        if (username.isEmpty()) {
            throw new NotAuthorizedUserException("Имя пользователя не должно быть пустым");
        }

        cartRepository.deactivateCart(username);
    }

    @Override
    public ShoppingCartDto remove(String username, Map<String, Long> products) {

        if(username.isEmpty()) {
            throw new NotAuthorizedUserException("Имя пользователя не должно быть пустым");
        }

        ShoppingCart shoppingCart = cartRepository.getShoppingCartByUsernameAndActive(username,true);

        if(shoppingCart.getPositions().isEmpty()) {
            throw new NoProductsInShoppingCartException("Нет искомых товаров в корзине");
        }

        shoppingCart.getPositions().stream()
                .filter(elem -> products.keySet().contains(elem.getProductId()))
                .peek(elem -> elem.setQuantity(products.get(elem.getProductId())));


        return mapper.toShoppingCartDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto changeQuantity(String username, ChangeProductQuantityRequest changeProductQuantityRequest) {

        if (username.isEmpty()) {
            throw new NotAuthorizedUserException("Имя пользователя не должно быть пустым");
        }

        ShoppingCart shoppingCart = cartRepository.getShoppingCartByUsernameAndActive(username, true);

        shoppingCart.getPositions().stream()
                .peek(elem -> {
                    if (elem.getProductId().equals(changeProductQuantityRequest.getProductId())) {
                        elem.setQuantity(changeProductQuantityRequest.getNewQuantity());
                    }

                });

        return mapper.toShoppingCartDto(shoppingCart);
    }

    @Override
    public BookedProductsDto booking(String username) {
        if (username.isEmpty()) {
            throw new NotAuthorizedUserException("Имя пользователя не должно быть пустым");
        }

        ShoppingCart shoppingCart = cartRepository.getShoppingCartByUsernameAndActive(username, true);

        return client.checkProd(mapper.toShoppingCartDto(shoppingCart));
    }
}
