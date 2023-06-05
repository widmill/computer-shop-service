package com.example.computershopservice.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Laptop extends Product {

    private Integer size;

    public Laptop() {
    }

    public Laptop(Long serialNumber, String producer, BigDecimal price, Integer amount, Integer size) {
        super(serialNumber, producer, price, amount);
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Product{" +
                "serialNumber=" + super.getSerialNumber() +
                ", producer='" + super.getProducer() +
                ", price=" + super.getPrice() +
                ", amount=" + super.getAmount() +
                ", size=" + size +
                '}';
    }
}
