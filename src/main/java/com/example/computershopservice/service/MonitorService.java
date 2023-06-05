package com.example.computershopservice.service;

import com.example.computershopservice.entity.Monitor;
import com.example.computershopservice.exception.EntityAlreadyExistsException;
import com.example.computershopservice.exception.EntityNotFoundException;
import com.example.computershopservice.exception.MissingDataException;
import com.example.computershopservice.repository.MonitorRepository;
import com.example.computershopservice.validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorService {

    private final MonitorRepository monitorRepository;
    private final Logger log = LoggerFactory.getLogger(MonitorService.class);

    public MonitorService(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    public Monitor addMonitorProduct(Monitor monitor) {
        log.info("Saving monitor product = {}", monitor);

        if (monitorRepository.findById(monitor.getSerialNumber()).isPresent()) {
            throw new EntityAlreadyExistsException("Продукт с таким серийным номером уже существует.");
        }

        ProductValidator.checkFields(monitor);
        if (monitor.getDiagonal() == null) {
            throw new MissingDataException("Поле с диагональю должно быть заполнено.");
        }

        return monitorRepository.save(monitor);
    }

    public Monitor getMonitorProduct(long serialNumber) {
        Monitor monitor = monitorRepository.findById(serialNumber).orElseThrow(() ->
                new EntityNotFoundException("Монитор с данным серийным номером не найден."));

        log.info("Found monitor product = {}", monitor);

        return monitor;
    }

    public List<Monitor> getAllMonitorProducts() {

        List<Monitor> monitors = monitorRepository.findAll();

        if (monitors.isEmpty()) {
            throw new EntityNotFoundException("Товары не были найдены.");
        }

        return monitors;
    }

    public Monitor updateMonitorProduct(Monitor monitor) {
        Monitor monitorToUpdate = monitorRepository.findById(monitor.getSerialNumber()).orElseThrow(() ->
                new EntityNotFoundException("Монитор с данным серийным номером не найден."));

        log.info("Product before update = {}", monitorToUpdate);

        if (monitor.getProducer() != null) monitorToUpdate.setProducer(monitor.getProducer());
        if (monitor.getPrice() != null) monitorToUpdate.setPrice(monitor.getPrice());
        if (monitor.getAmount() != null) monitorToUpdate.setAmount(monitor.getAmount());
        if (monitor.getDiagonal() != null) monitorToUpdate.setDiagonal(monitor.getDiagonal());

        log.info("Product after update = {}", monitorToUpdate);

        return monitorRepository.save(monitorToUpdate);
    }
}
