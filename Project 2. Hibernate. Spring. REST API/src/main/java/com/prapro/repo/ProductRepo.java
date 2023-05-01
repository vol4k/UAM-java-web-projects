package com.prapro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prapro.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
  List<Product> findById(int id);

  List<Product> findByNameContaining(String name);

  List<Product> findByCompanyId(Long companyid);
}