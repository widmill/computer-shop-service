package com.example.computershopservice.service;

import com.example.computershopservice.entity.HDD;
import com.example.computershopservice.exception.EntityAlreadyExistsException;
import com.example.computershopservice.exception.EntityNotFoundException;
import com.example.computershopservice.exception.MissingDataException;
import com.example.computershopservice.repository.HddRepository;
import com.example.computershopservice.validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HddService {

    private final HddRepository hddRepository;
    private final Logger log = LoggerFactory.getLogger(HddService.class);

    public HddService(HddRepository hddRepository) {
        this.hddRepository = hddRepository;
    }

    public HDD addHddProduct(HDD hdd) {
        log.info("Saving HDD product = {}", hdd);

        if (hddRepository.findById(hdd.getSerialNumber()).isPresent()) {
            throw new EntityAlreadyExistsException("Продукт с таким серийным номером уже существует.");
        }

        ProductValidator.checkFields(hdd);
        if (hdd.getMemorySize() == null) {
            throw new MissingDataException("Поле с объемом памяти должно быть заполнено.");
        }

        return hddRepository.save(hdd);
    }

    public HDD getHddProduct(long serialNumber) {
        HDD hdd = hddRepository.findById(serialNumber).orElseThrow(() ->
                new EntityNotFoundException("Жесткий диск с данным серийным номером не найден."));

        log.info("Found HDD product = {}", hdd);

        return hdd;
    }

    public List<HDD> getAllHddProducts() {
        List<HDD> list = hddRepository.findAll();

        if (list.isEmpty()) {
            throw new EntityNotFoundException("Товары не были найдены.");
        }

        return list;

    }

    public HDD updateHddProduct(HDD hdd) {
        HDD hddToUpdate = hddRepository.findById(hdd.getSerialNumber()).orElseThrow(() ->
                new EntityNotFoundException("Жесткий диско с данным серийным номером не найден."));

        if (hdd.getProducer() != null) hddToUpdate.setProducer(hdd.getProducer());
        if (hdd.getPrice() != null) hddToUpdate.setPrice(hdd.getPrice());
        if (hdd.getAmount() != null) hddToUpdate.setAmount(hdd.getAmount());
        if (hdd.getMemorySize() != null) hddToUpdate.setMemorySize(hdd.getMemorySize());

        return hddRepository.save(hddToUpdate);
    }
}
