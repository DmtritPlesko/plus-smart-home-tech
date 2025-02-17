package ru.yandex.practicum.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.dto.shopping.ShoppingCartDto;
import ru.yandex.practicum.model.ShoppingCart;

@Component
public class ShoppingCartMapperImpl implements ShoppingCartMapper {

    @Override
    public ShoppingCartDto toShoppingCartDto(ShoppingCart cart) {
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();

        shoppingCartDto.setShoppingCartId(cart.getCartId());
        shoppingCartDto.setProducts(shoppingCartDto.getProducts());

        return shoppingCartDto;
    }

    @Override
    public ShoppingCart toShoppingCart(ShoppingCartDto shoppingCartDto) {
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setCartId(shoppingCart.getCartId());
        shoppingCart.setPositions(shoppingCart.getPositions());

        return shoppingCart;
    }
}
