package com.prapro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "companyid")
  private int companyId;

  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private Depot depot;

  public Product() {
    this.name = "";
  }

  public Product(String name, int companyId) {
    this.name = name;
    this.companyId = companyId;
  }

  public Product(int id, String name, int companyId) {
    this.id = id;
    this.name = name;
    this.companyId = companyId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getcompanyId() {
    return companyId;
  }

  public void setcompanyId(int companyId) {
    this.companyId = companyId;
  }
}
