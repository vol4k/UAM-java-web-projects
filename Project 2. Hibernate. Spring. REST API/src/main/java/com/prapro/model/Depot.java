package com.prapro.model;

import java.time.ZonedDateTime;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "depot")
public class Depot {

  @Column(name = "shopid")
  private int shopId;

  @Id
  @Column(name = "productid")
  private int productId;

  @Column(name = "date")
  private ZonedDateTime date;

  @OneToOne(mappedBy = "depot")
  private Product product;

  @OneToMany(mappedBy = "depot")
  private Set<Shop> shops;

  public Depot() {
    date = ZonedDateTime.now();
  }

  public Depot(int shopId, int productId) {
    this.shopId = shopId;
    this.productId = productId;
    this.date = ZonedDateTime.now();
  }

  public Depot(int shopId, int productId, ZonedDateTime date) {
    this.shopId = shopId;
    this.productId = productId;
    this.date = date;
  }

  public int getshopId() {
    return shopId;
  }

  public void setshopId(int shopId) {
    this.shopId = shopId;
  }

  public int getproductId() {
    return productId;
  }

  public void setproductId(int productId) {
    this.productId = productId;
  }

  public ZonedDateTime getDate() {
    return date;
  }

  public void setDate(ZonedDateTime date) {
    this.date = date;
  }

}
