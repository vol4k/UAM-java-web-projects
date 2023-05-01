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

import com.prapro.model.Company;
import com.prapro.repo.CompanyRepo;

@RestController
@RequestMapping("/api")
public class CompaniesController {

  @Autowired
  CompanyRepo companyRepo;

  @CrossOrigin
  @GetMapping("/companies")
  public ResponseEntity<List<Company>> getAllCompanies(@RequestParam(required = false) String name) {
    try {
      List<Company> companies = new ArrayList<Company>();

      if (name == null)
        companyRepo.findAll().forEach(companies::add);
      else
        companyRepo.findByNameContaining(name).forEach(companies::add);

      return new ResponseEntity<>(companies, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @GetMapping("/companies/{id}")
  public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id) {
    Optional<Company> companyData = companyRepo.findById(id);

    if (companyData.isPresent()) {
      return new ResponseEntity<>(companyData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @CrossOrigin
  @PostMapping("/companies")
  public ResponseEntity<Company> createCompany(@RequestBody Company company) {
    try {
      Company _company = companyRepo
          .save(new Company(company.getId(), company.getName()));
      return new ResponseEntity<>(_company, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @PutMapping("/companies/{id}")
  public ResponseEntity<Company> updateCompany(@PathVariable("id") Long id, @RequestBody Company company) {
    Optional<Company> companyData = companyRepo.findById(id);

    if (companyData.isPresent()) {
      Company _company = companyData.get();
      _company.setName(company.getName());
      return new ResponseEntity<>(companyRepo.save(_company), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @CrossOrigin
  @DeleteMapping("/companies/{id}")
  public ResponseEntity<HttpStatus> deleteCompany(@PathVariable("id") long id) {
    try {
      companyRepo.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin
  @DeleteMapping("/companies")
  public ResponseEntity<HttpStatus> deleteAllCompanies() {
    try {
      companyRepo.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
}