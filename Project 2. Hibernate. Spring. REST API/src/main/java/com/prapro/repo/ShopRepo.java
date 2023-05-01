package com.prapro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prapro.model.Shop;

public interface ShopRepo extends JpaRepository<Shop, Long> {
  List<Shop> findById(int id);

  List<Shop> findByNameContaining(String name);
}