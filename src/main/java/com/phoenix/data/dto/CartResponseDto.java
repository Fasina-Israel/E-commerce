package com.phoenix.data.dto;

import com.phoenix.data.model.Item;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class CartResponseDto {

    private List<Item> cartItems;

    private double totalPrice;

}
