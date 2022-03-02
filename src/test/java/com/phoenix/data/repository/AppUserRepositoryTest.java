package com.phoenix.data.repository;

import com.phoenix.data.model.AppUser;
import com.phoenix.data.model.Cart;
import com.phoenix.data.model.Item;
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
@Slf4j
@Sql(scripts = "/db/insert.sql")
class AppUserRepositoryTest {

    @Autowired
    AppUserRepository appUserRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Create a new user with cart test")
    void whenNewUserIsCreated_thenCreateCartTest(){
        //creating a user Object
        AppUser appUser = new AppUser();
        appUser.setFirstName("John");
        appUser.setLastName("Badmus");
        appUser.setAddress("123 peace road");
        appUser.setEmail("john@yupmail.com");
        //save user
        appUserRepository.save(appUser);

        assertThat(appUser.getId()).isNotNull();
        assertThat(appUser.getMyCart()).isNotNull();

        log.info("App user created :: {}",appUser);
    }

    @Test
    @DisplayName("")
    void updateCartItemTest(){
        AppUser appUser = appUserRepository.findById(updateDto.getUserId()).orElse(null);
        assertThat(appUser).isNotNull();
        Cart userCart = appUser.getMyCart();
        assertThat(userCart.getItemList().size()).isEqualTo(1);
        Item item = userCart.getItemList().get(0);
        log.info("Item --> ite")
    }
}