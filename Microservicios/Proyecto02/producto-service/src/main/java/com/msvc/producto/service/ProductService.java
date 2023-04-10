package com.msvc.producto.service;

import com.msvc.producto.entity.Product;

import java.util.List;

public interface ProductService {

    Product createProducts(Product product);

    List<Product> getAllProducts();
}
