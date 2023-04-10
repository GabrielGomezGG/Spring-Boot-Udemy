package com.msvc.producto.repository;

import com.msvc.producto.dto.ProductResponse;
import com.msvc.producto.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,Long> {

}
