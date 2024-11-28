package com.anup.product_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.anup.product_service.dto.ProductRequest;
import com.anup.product_service.dto.ProductResponse;
import com.anup.product_service.repository.ProductRepository;
import com.anup.product_service.model.Product;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    private ProductResponse mapToProdRes(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getName())
                .description(product.getDescription()).price(product.getPrice()).build();
    }

    @Transactional
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();

        productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map((t -> mapToProdRes(t))).toList();
    }

    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id).get();
        ProductResponse productResponse = mapToProdRes(product);

        return productResponse;
    }
}
