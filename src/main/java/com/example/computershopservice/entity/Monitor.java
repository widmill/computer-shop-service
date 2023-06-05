package com.example.computershopservice.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Monitor extends Product {

    private Integer diagonal;

    public Monitor() {

    }

    public Monitor(Long serialNumber, String producer, BigDecimal price, Integer amount, Integer diagonal) {
        super(serialNumber, producer, price, amount);
        this.diagonal = diagonal;
    }

    public Integer getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(Integer diagonal) {
        this.diagonal = diagonal;
    }

    @Override
    public String toString() {
        return "Product{" +
                "serialNumber=" + super.getSerialNumber() +
                ", producer='" + super.getProducer() +
                ", price=" + super.getPrice() +
                ", amount=" + super.getAmount() +
                ", diagonal=" + diagonal +
                '}';
    }
}
