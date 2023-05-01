package com.prapro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shop")
public class Shop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @ManyToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private Depot depot;

  public Shop() {
    this.name = "";
  }

  public Shop(int id, String name) {
    this.id = id;
    this.name = name;
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

}
