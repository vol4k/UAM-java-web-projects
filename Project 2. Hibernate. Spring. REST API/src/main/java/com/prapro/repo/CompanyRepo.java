package com.prapro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prapro.model.Company;

public interface CompanyRepo extends JpaRepository<Company, Long> {
  List<Company> findById(int id);

  List<Company> findByNameContaining(String name);
}