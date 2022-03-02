package com.phoenix.service.cart;

import com.github.fge.jsonpatch.JsonPatch;
import com.phoenix.data.dto.CartRequestDto;
import com.phoenix.data.dto.CartResponseDto;
import com.phoenix.data.model.AppUser;
import com.phoenix.data.model.Cart;
import com.phoenix.data.model.Item;
import com.phoenix.data.model.Product;
import com.phoenix.data.repository.AppUserRepository;
import com.phoenix.data.repository.CartRepository;
import com.phoenix.data.repository.ProductRepository;
import com.phoenix.web.exceptions.BussinessLogicException;
import com.phoenix.web.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements  CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public CartResponseDto addItemToCart(CartRequestDto cartItemDto) throws UserNotFoundException, BussinessLogicException {
        //Check If User Exist
        Optional<AppUser> query = appUserRepository.findById(cartItemDto.getUserId());
        if(query.isEmpty()) {throw new UserNotFoundException("User with Id no "+ cartItemDto.getUserId()+" is not found");}


        return null;
    }

    private Double calculateItemPrice(Item item) {
        return item.getProduct().getPrice() * item.getQuantityAddedToCart();
    }

    private CartResponseDto buildCartResponse(Cart cart){
        return CartResponseDto.builder()
                .cartItems(cart.getItemList())
                .totalPrice(cart.getTotalPrice())
                .build();
    }

    private  boolean quantityIsValid(Product product, int quantity){
        return product.getQuantity() < quantity;
    }

    @Override
    public Cart viewCart() {
        return null;
    }

    @Override
    public CartResponseDto updateCart(Long userId, Long itemId, JsonPatch patch) {
        //get a cart by userId

        //Find an item within cart with itemId

        //perform update to item

        return null;
    }
}
