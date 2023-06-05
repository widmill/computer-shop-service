package com.example.computershopservice.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Computer extends Product {

    //0 - десктоп, 1 - неттоп, 2 - моноблок
    private Short type;

    public Computer(Long serialNumber, String producer, BigDecimal price, Integer amount, Short type) {
        super(serialNumber, producer, price, amount);
        this.type = type;
    }

    public Computer() {

    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "serialNumber=" + super.getSerialNumber() +
                ", producer='" + super.getProducer() +
                ", price=" + super.getPrice() +
                ", amount=" + super.getAmount() +
                ", type=" + type +
                '}';
    }
}