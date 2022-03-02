package com.phoenix.data.repository;

//import com.phoenix.data.model.Cart;
//import com.phoenix.data.model.Item;
import com.phoenix.data.model.Cart;
import com.phoenix.data.model.Item;
import com.phoenix.data.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Sql(scripts = {"/db/insert.sql"})
@Slf4j
class CartRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartRepository cartRepository;
    @BeforeEach
    void setUp() {
    }

    @Test@DisplayName("Add an item to cart test")
    void addProductToCart(){
        Product product = productRepository.findByName("Macbook Air").orElse(null);
        assertThat(product).isNotNull();
        Item item = new Item(product,2);
        assertThat(item.getProduct()).isNotNull();
        Cart cart = new Cart();
        cart.addItem(item);


        cartRepository.save(cart);
        assertThat(cart.getId()).isNotNull();
        assertThat(cart.getItemList().isEmpty()).isFalse();
        assertThat(cart.getItemList().get(0).getProduct()).isNotNull();
    }

    @Test
    @DisplayName("View all items in a cart")
    void viewItemsInCartTest(){

        Cart savedCart = cartRepository.findById(345L).orElse(null);
        assertThat(savedCart).isNotNull();
        assertThat(savedCart.getItemList().size()).isEqualTo(3);
        //print items
        log.info("Cart Retrieved from DB ::{}", savedCart);
    }
}