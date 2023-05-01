package com.prapro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.prapro.model.Depot;
import com.prapro.repo.DepotRepo;

@RestController
@RequestMapping("/api")
public class DepotsController {

  @Autowired
  DepotRepo depotRepo;

  @CrossOrigin
  @GetMapping("/depots")
  public ResponseEntity<List<Depot>> getAllDepots(@RequestParam(required = false) Long shopId,
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
    try {
      List<Depot> depots = new ArrayList<Depot>();

      Pageable paging = PageRequest.of(page, size);

      if (shopId == null)
        depotRepo.findAll(paging).forEach(depots::add);
      else
        depotRepo.findByShopId(shopId, paging).forEach(depots::add);

      return new ResponseEntity<>(depots, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @GetMapping("/depots/{productId}")
  public ResponseEntity<Depot> getDepotById(@PathVariable("productId") Long productId) {
    Optional<Depot> depotData = depotRepo.findByProductId(productId);

    if (depotData.isPresent()) {
      return new ResponseEntity<>(depotData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @CrossOrigin
  @PostMapping("/depots")
  public ResponseEntity<Depot> createDepot(@RequestBody Depot depot) {
    try {
      Depot _depot = depotRepo
          .save(new Depot(depot.getshopId(), depot.getproductId()));
      return new ResponseEntity<>(_depot, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @PutMapping("/depots/{productId}")
  public ResponseEntity<Depot> updateDepot(@PathVariable("productId") Long productId, @RequestBody Depot depot) {
    Optional<Depot> depotData = depotRepo.findByProductId(productId);

    if (depotData.isPresent()) {
      Depot _depot = depotData.get();
      _depot.setshopId(depot.getshopId());
      _depot.setDate(depot.getDate());
      return new ResponseEntity<>(depotRepo.save(_depot), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @CrossOrigin
  @DeleteMapping("/depots/{productId}")
  public ResponseEntity<HttpStatus> deleteDepot(@PathVariable("productId") long productId) {
    try {
      depotRepo.deleteByProductId(productId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @DeleteMapping("/depots")
  public ResponseEntity<HttpStatus> deleteAllDepots() {
    try {
      depotRepo.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
}