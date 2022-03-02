package com.phoenix.data.dto;

import com.phoenix.data.model.Item;
import lombok.Data;

import java.util.List;

@Data
public class CartRequestDto {

    private Long userId;

    private Long productId;

    private Integer quantity;
}
