package com.example.computershopservice.controller;

import com.example.computershopservice.dto.LaptopDto;
import com.example.computershopservice.entity.Laptop;
import com.example.computershopservice.service.LaptopService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/laptops")
public class LaptopController {

    private final LaptopService laptopService;
    private final ModelMapper mapper;

    public LaptopController(LaptopService laptopService, ModelMapper mapper) {
        this.laptopService = laptopService;
        this.mapper = mapper;
    }

    /**
     * Возвращает все товары типа ноутбук
     *
     * @return список из товаров
     */
    @GetMapping
    public List<LaptopDto> getAllLaptopProducts() {

        return laptopService.getAllLaptopProducts()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Поиск ноутбука по серийному номеру
     *
     * @param serialNumber серийный номер, по которому идет поиск
     * @return Найденный продукт
     */
    @GetMapping("/{serialNumber}")
    public LaptopDto getLaptopProduct(@PathVariable Long serialNumber) {

        return mapToDto(laptopService.getLaptopProduct(serialNumber));
    }

    /**
     * Метод создания продукта
     *
     * @param dto тело запроса для создания
     * @return созданный продукт
     */
    @PostMapping
    public LaptopDto addLaptopProduct(@RequestBody LaptopDto dto) {

        return mapToDto(laptopService.addLaptopProduct(mapToLaptop(dto)));
    }

    /**
     * Метод редактирования продукта
     *
     * @param dto тело запроса для изменения
     * @return измененный продукт
     */
    @PutMapping
    public LaptopDto updateLaptopProduct(@RequestBody LaptopDto dto) {
        return mapToDto(laptopService.updateLaptopProduct(mapToLaptop(dto)));
    }

    private Laptop mapToLaptop(LaptopDto dto) {
        return mapper.map(dto, Laptop.class);
    }

    private LaptopDto mapToDto(Laptop laptop) {
        return mapper.map(laptop, LaptopDto.class);
    }


}
