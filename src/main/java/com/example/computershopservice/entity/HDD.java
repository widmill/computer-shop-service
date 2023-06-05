package com.example.computershopservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class HDD extends Product {

    @Column(name = "memory_size_gb")
    private Long memorySize;

    public HDD() {
    }

    public HDD(Long serialNumber, String producer,
               BigDecimal price, Integer amount, Long memorySize) {
        super(serialNumber, producer, price, amount);
        this.memorySize = memorySize;
    }

    public Long getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(Long memorySize) {
        this.memorySize = memorySize;
    }

    @Override
    public String toString() {
        return "Product{" +
                "serialNumber=" + super.getSerialNumber() +
                ", producer='" + super.getProducer() +
                ", price=" + super.getPrice() +
                ", amount=" + super.getAmount() +
                ", memorySize=" + memorySize +
                '}';
    }
}
