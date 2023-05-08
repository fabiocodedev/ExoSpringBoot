package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.bean.Product;
import com.example.demo.bean.User;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	User findByTitre (String titre);
}
