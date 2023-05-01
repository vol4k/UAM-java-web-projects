package com.prapro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prapro.model.User;
import com.prapro.repo.UserRepo;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  UserRepo userRepo;

  @CrossOrigin
  @PostMapping("/users")
  public ResponseEntity<List<User>> getAllUsers(@RequestBody User user) {
    try {
      List<User> users = userRepo.findByNameAndPassword(user.getName(), user.getPassword());

      return new ResponseEntity<>(users, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}