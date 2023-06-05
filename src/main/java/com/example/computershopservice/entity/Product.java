package com.example.computershopservice.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@MappedSuperclass
public abstract class Product {

    @Id
    private Long serialNumber;

    private String producer;

    private BigDecimal price;

    private Integer amount;

    public Product() {
    }

    public Product(Long serialNumber, String producer, BigDecimal price, Integer amount) {
        this.serialNumber = serialNumber;
        this.producer = producer;
        this.price = price;
        this.amount = amount;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "serialNumber=" + serialNumber +
                ", producer='" + producer + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}

