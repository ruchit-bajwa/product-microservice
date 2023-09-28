package com.full6Hours.controller;


import com.full6Hours.dto.ProductRequest;
import com.full6Hours.dto.ProductResponse;
import com.full6Hours.modelEntity.Product;
import com.full6Hours.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody ProductRequest productRequest)
    {
        return productService.createProduct(productRequest);

    }
    @GetMapping("/view")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts()
    {
        return productService.getALLProducts();

    }
}
