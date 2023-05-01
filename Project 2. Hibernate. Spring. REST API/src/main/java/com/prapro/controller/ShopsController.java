package com.prapro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prapro.model.Shop;
import com.prapro.repo.ShopRepo;

@RestController
@RequestMapping("/api")
public class ShopsController {

  @Autowired
  ShopRepo shopRepo;

  @CrossOrigin
  @GetMapping("/shops")
  public ResponseEntity<List<Shop>> getAllShops(@RequestParam(required = false) String name) {
    try {
      List<Shop> shops = new ArrayList<Shop>();

      if (name == null)
        shopRepo.findAll().forEach(shops::add);
      else
        shopRepo.findByNameContaining(name).forEach(shops::add);

      return new ResponseEntity<>(shops, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @GetMapping("/shops/{id}")
  public ResponseEntity<Shop> getShopById(@PathVariable("id") Long id) {
    Optional<Shop> shopData = shopRepo.findById(id);

    if (shopData.isPresent()) {
      return new ResponseEntity<>(shopData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @CrossOrigin
  @PostMapping("/shops")
  public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
    try {
      Shop _shop = shopRepo
          .save(new Shop(shop.getId(), shop.getName()));
      return new ResponseEntity<>(_shop, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @PutMapping("/shops/{id}")
  public ResponseEntity<Shop> updateShop(@PathVariable("id") Long id, @RequestBody Shop shop) {
    Optional<Shop> shopData = shopRepo.findById(id);

    if (shopData.isPresent()) {
      Shop _shop = shopData.get();
      _shop.setName(shop.getName());
      return new ResponseEntity<>(shopRepo.save(_shop), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @CrossOrigin
  @DeleteMapping("/shops/{id}")
  public ResponseEntity<HttpStatus> deleteShop(@PathVariable("id") long id) {
    try {
      shopRepo.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @DeleteMapping("/shops")
  public ResponseEntity<HttpStatus> deleteAllShops() {
    try {
      shopRepo.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
}