package com.prapro.repo;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prapro.model.Depot;

import jakarta.transaction.Transactional;

public interface DepotRepo extends JpaRepository<Depot, Long> {
  @Transactional
  Page<Depot> findByShopId(Long shopId, Pageable pageable);

  @Transactional
  Optional<Depot> findByProductId(Long productid);

  @Transactional
  List<Depot> findByDate(Date date);

  @Transactional
  void deleteByProductId(Long productid);
}