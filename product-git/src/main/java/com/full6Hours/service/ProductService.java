package com.full6Hours.service;

import com.full6Hours.dto.ProductRequest;
import com.full6Hours.dto.ProductResponse;
import com.full6Hours.modelEntity.Product;
import com.full6Hours.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {


    @Autowired
    ProductRepository productRepository;

    public Product createProduct(ProductRequest productRequest)
    {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        log.info("Product {} is saved ",product);

        return productRepository.save(product);
    }

    public List<ProductResponse> getALLProducts() {

        List<Product> responseList = productRepository.findAll();
        return responseList.stream().map(this::getProductResponse).collect(Collectors.toList());
    }

    private ProductResponse getProductResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

    }
}
