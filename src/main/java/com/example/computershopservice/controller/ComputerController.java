package com.example.computershopservice.controller;

import com.example.computershopservice.dto.ComputerDto;
import com.example.computershopservice.entity.Computer;
import com.example.computershopservice.service.ComputerService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/computers")
public class ComputerController {

    private final ComputerService computerService;
    private final ModelMapper mapper;

    public ComputerController(ComputerService computerService, ModelMapper mapper) {
        this.computerService = computerService;
        this.mapper = mapper;
    }

    /**
     * Метод поиска настольного компьютера по серийному номеру
     *
     * @param serialNumber номер серии, по которому ищется компьютер
     * @return найденный компьютер
     */
    @GetMapping("/{serialNumber}")
    public ComputerDto getComputerProduct(@PathVariable long serialNumber) {
        return mapToDto(computerService.getComputerProduct(serialNumber));
    }

    /**
     * Возвращает все товары типа настольный компьютер
     *
     * @return список из товаров
     */
    @GetMapping
    public List<ComputerDto> getAllComputerProducts() {
        return computerService.getAllComputerProducts()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Метод создания продукта
     *
     * @param dto тело запроса для создания
     * @return созданый продукт
     */
    @PostMapping
    public ComputerDto addComputerProduct(@RequestBody ComputerDto dto) {
        return mapToDto(computerService.addComputerProduct(mapToComputer(dto)));
    }

    /**
     * Метод редактирования продукта
     *
     * @param dto тело запроса для изменения
     * @return измененный продукт
     */
    @PutMapping
    public ComputerDto updateComputerProduct(@RequestBody ComputerDto dto) {
        return mapToDto(computerService.updateComputerProduct(mapToComputer(dto)));
    }

    private Computer mapToComputer(ComputerDto dto) {
        return mapper.map(dto, Computer.class);
    }

    private ComputerDto mapToDto(Computer computer) {
        return mapper.map(computer, ComputerDto.class);
    }
}
