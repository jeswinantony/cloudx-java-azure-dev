package com.chtrembl.petstore.product.service;

import com.chtrembl.petstore.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStatusIn(List<Product.Status> statuses);
}
