package com.msvc.producto.controller;

import com.msvc.producto.dto.ProductRequest;
import com.msvc.producto.dto.ProductResponse;
import com.msvc.producto.entity.Product;
import com.msvc.producto.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct(){

        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping()
    public ResponseEntity<Product> saveProduct(@RequestBody Product productRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createProducts(productRequest));
    }
}
