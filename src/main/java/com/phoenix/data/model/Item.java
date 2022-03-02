package com.phoenix.data.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    private int quantityAddedToCart;

    public Item(Product product, int quantityAddedToCart){
        if(quantityAddedToCart <= product.getQuantity())
            this.quantityAddedToCart = quantityAddedToCart;
        else {
            this.quantityAddedToCart = 0;
        }
        this.product  = product;
    }

}
