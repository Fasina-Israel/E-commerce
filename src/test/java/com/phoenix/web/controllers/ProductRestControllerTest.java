package com.phoenix.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.data.model.Product;
import com.phoenix.data.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/db/insert.sql"})
class ProductRestControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Autowired
    ProductRepository productRepository;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Get product api test")
    void getProductsTest() throws Exception{
        mockMvc.perform(get("/api/product").contentType("application/json")).andExpect(status().is(200)).andDo(print());
    }

    @Test
    void findAllProducts() {
    }

    @Test
    @DisplayName("Create a product api Test")
    void createProduct()throws Exception {
        Product product = new Product();
        product.setName("Bamboo Chair");
        product.setDescription("World Class bamboo");
        product.setPrice(5540);
        product.setQuantity(9);

        String requestBody = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/api/product")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().is(400))
                .andDo(print());
    }

    @Test
    void updateProductTest() throws Exception {
        Product product = productRepository.findById(14L).orElse(null);
        assertThat(product).isNotNull();

        mockMvc.perform(patch("/api/product/14").
                contentType("application/json-patch+json").
                content(Files.readAllBytes(Path.of("payload.json")))).
                andExpect(status().is(200)).
                andDo(print());

        product = productRepository.findById(14L).orElse(null);
        assertThat(product).isNotNull();
        assertThat(product.getDescription()).isEqualTo("spring frontend");

    }
}