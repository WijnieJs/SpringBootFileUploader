package com.example.novidemoimg.repository;

import com.example.novidemoimg.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, Long> {


}
