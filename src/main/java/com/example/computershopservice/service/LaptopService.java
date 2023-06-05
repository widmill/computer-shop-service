package com.example.computershopservice.service;

import com.example.computershopservice.entity.Laptop;
import com.example.computershopservice.exception.EntityAlreadyExistsException;
import com.example.computershopservice.exception.EntityNotFoundException;
import com.example.computershopservice.exception.MissingDataException;
import com.example.computershopservice.repository.LaptopRepository;
import com.example.computershopservice.validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopService {

    private final LaptopRepository laptopRepository;
    private final Logger log = LoggerFactory.getLogger(LaptopService.class);

    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    public Laptop addLaptopProduct(Laptop laptop) {
        log.info("Saving laptop product = {}", laptop);

        if (laptopRepository.findById(laptop.getSerialNumber()).isPresent()) {
            throw new EntityAlreadyExistsException("Продукт с таким серийным номером уже существует.");
        }

        ProductValidator.checkFields(laptop);
        if (laptop.getSize() == null) {
            throw new MissingDataException("Поле с размером ноутбука должно быть заполнено.");
        }

        return laptopRepository.save(laptop);
    }

    public Laptop getLaptopProduct(long serialNumber) {
        Laptop laptop = laptopRepository.findById(serialNumber).orElseThrow(() ->
                new EntityNotFoundException("Ноутбук с данным серийным номером не был найден."));

        log.info("Found laptop product = {}", laptop);

        return laptop;
    }

    public List<Laptop> getAllLaptopProducts() {
        List<Laptop> laptops = laptopRepository.findAll();

        if (laptops.isEmpty()) {
            throw new EntityNotFoundException("Товары не были найдены.");
        }


        return laptops;
    }

    public Laptop updateLaptopProduct(Laptop laptop) {
        Laptop laptopToUpdate = laptopRepository.findById(laptop.getSerialNumber()).orElseThrow(() ->
                new EntityNotFoundException("Ноутбук с данным серийным номером не был найден."));

        log.info("Product before update = {}", laptopToUpdate);

        if (laptop.getProducer() != null) laptopToUpdate.setProducer(laptop.getProducer());
        if (laptop.getPrice() != null) laptopToUpdate.setPrice(laptop.getPrice());
        if (laptop.getAmount() != null) laptopToUpdate.setAmount(laptop.getAmount());
        if (laptop.getSize() != null) laptopToUpdate.setSize(laptop.getSize());

        log.info("Product after update = {}", laptopToUpdate);

        return laptopRepository.save(laptopToUpdate);
    }

}
