package com.chtrembl.petstore.product.service;

import com.chtrembl.petstore.product.model.DataPreload;
import com.chtrembl.petstore.product.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findProductsByStatus(List<String> status) {
        log.info("Finding products with status: {}", status);

        var statuses = status.stream().map(Product.Status::valueOf).toList();
        return productRepository.findByStatusIn(statuses);
    }

    public Optional<Product> findProductById(Long productId) {
        log.info("Finding product with id: {}", productId);

        return productRepository.findById(productId);
    }

    public List<Product> getAllProducts() {
        log.info("Getting all products");
        return productRepository.findAll();
    }

    public int getProductCount() {
        return (int) productRepository.count();
    }
}