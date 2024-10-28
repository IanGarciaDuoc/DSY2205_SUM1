package com.tienda.tienda_backend.repository;

import com.tienda.tienda_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
