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

import com.prapro.model.Product;
import com.prapro.repo.ProductRepo;

@RestController
@RequestMapping("/api")
public class ProductsController {

  @Autowired
  ProductRepo productRepo;

  @CrossOrigin
  @GetMapping("/products")
  public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
    try {
      List<Product> products = new ArrayList<Product>();

      if (name == null)
        productRepo.findAll().forEach(products::add);
      else
        productRepo.findByNameContaining(name).forEach(products::add);

      return new ResponseEntity<>(products, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @GetMapping("/products/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
    Optional<Product> productData = productRepo.findById(id);

    if (productData.isPresent()) {
      return new ResponseEntity<>(productData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @CrossOrigin
  @PostMapping("/products")
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    try {
      Product _product = productRepo
          .save(new Product(product.getName(), product.getcompanyId()));
      return new ResponseEntity<>(_product, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @PutMapping("/products/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
    Optional<Product> productData = productRepo.findById(id);

    if (productData.isPresent()) {
      Product _product = productData.get();
      _product.setName(product.getName());
      _product.setcompanyId(product.getcompanyId());
      return new ResponseEntity<>(productRepo.save(_product), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @CrossOrigin
  @DeleteMapping("/products/{id}")
  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
    try {
      productRepo.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @DeleteMapping("/products")
  public ResponseEntity<HttpStatus> deleteAllProducts() {
    try {
      productRepo.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
}