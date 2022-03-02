package com.phoenix.service.product;

import com.github.fge.jsonpatch.JsonPatch;
import com.phoenix.data.dto.ProductDto;
import com.phoenix.data.model.Product;
import com.phoenix.web.exceptions.BussinessLogicException;
import com.phoenix.web.exceptions.ProductDoesNotExistException;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product findProductById(Long productId) throws ProductDoesNotExistException;

    Product createProduct(ProductDto productDto) throws BussinessLogicException;

    Product updateProductDetails(Long productId, JsonPatch product) throws BussinessLogicException;
}
