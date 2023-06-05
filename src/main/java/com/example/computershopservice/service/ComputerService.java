package com.example.computershopservice.service;

import com.example.computershopservice.entity.Computer;
import com.example.computershopservice.exception.EntityAlreadyExistsException;
import com.example.computershopservice.exception.EntityNotFoundException;
import com.example.computershopservice.exception.MissingDataException;
import com.example.computershopservice.repository.ComputerRepository;
import com.example.computershopservice.validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {

    private final ComputerRepository computerRepository;
    private final Logger log = LoggerFactory.getLogger(ComputerService.class);

    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }


    public Computer addComputerProduct(Computer computer) {
        log.info("Saving computer product = {}", computer);

        if (computerRepository.findById(computer.getSerialNumber()).isPresent()) {
            throw new EntityAlreadyExistsException("Продукт с таким серийным номером уже существует.");
        }

        ProductValidator.checkFields(computer);
        checkType(computer.getType());


        return computerRepository.save(computer);
    }

    public Computer getComputerProduct(long serialNumber) {
        Computer computer = computerRepository.findById(serialNumber).orElseThrow(()
                -> new EntityNotFoundException("Компьютер с данным серийным номером не найден."));

        log.info("Found computer product = {}", computer);

        return computer;
    }

    public List<Computer> getAllComputerProducts() {

        List<Computer> computers = computerRepository.findAll();

        if (computers.isEmpty()) {
            throw new EntityNotFoundException("Товары не были найдены.");
        }

        return computers;
    }

    public Computer updateComputerProduct(Computer computer) {
        Computer computerToUpdate = computerRepository.findById(computer.getSerialNumber()).orElseThrow(()
                -> new EntityNotFoundException("Компьютер с таким серийным номер не найден."));

        log.info("Product before update = {}", computerToUpdate);

        if (computer.getProducer() != null) computerToUpdate.setProducer(computer.getProducer());
        if (computer.getPrice() != null) computerToUpdate.setPrice(computer.getPrice());
        if (computer.getAmount() != null) computerToUpdate.setAmount(computer.getAmount());
        if (computer.getType() != null) computerToUpdate.setType(computer.getType());

        log.info("Product after update = {}", computerToUpdate);

        return computerRepository.save(computerToUpdate);
    }

    private void checkType(Short type) {
        if (type == null) {
            throw new MissingDataException("Поле с типом компьютера должно быть заполнено.");
        }

        if (type != 0 && type != 1 && type != 2) {
            throw new MissingDataException("Типа настольного компьютера под числом " + type + " не существует.");
        }
    }
}
