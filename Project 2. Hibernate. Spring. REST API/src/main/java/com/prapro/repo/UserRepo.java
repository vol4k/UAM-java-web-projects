package com.prapro.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prapro.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
  List<User> findByNameAndPassword(String name, String password);
}