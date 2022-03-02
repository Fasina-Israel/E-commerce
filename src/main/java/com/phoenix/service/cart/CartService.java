package com.phoenix.service.cart;

import com.github.fge.jsonpatch.JsonPatch;
import com.phoenix.data.dto.CartRequestDto;
import com.phoenix.data.dto.CartResponseDto;
import com.phoenix.data.dto.CartUpdateDto;
import com.phoenix.data.model.Cart;
import com.phoenix.web.exceptions.BussinessLogicException;
import com.phoenix.web.exceptions.ProductDoesNotExistException;
import com.phoenix.web.exceptions.UserNotFoundException;

public interface CartService  {
    CartResponseDto addItemToCart(CartRequestDto cart)
            throws UserNotFoundException, BussinessLogicException, ProductDoesNotExistException;
    Cart viewCart();
    CartResponseDto updateCartItem(CartUpdateDto cartUpdateDton);
}
