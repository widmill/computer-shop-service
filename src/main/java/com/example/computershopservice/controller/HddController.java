package com.example.computershopservice.controller;

import com.example.computershopservice.dto.HddDto;
import com.example.computershopservice.entity.HDD;
import com.example.computershopservice.service.HddService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hdds")
public class HddController {

    private final HddService hddService;
    private final ModelMapper mapper;

    public HddController(HddService hddService, ModelMapper mapper) {
        this.hddService = hddService;
        this.mapper = mapper;
    }

    /**
     * Метод поиска жесткого диска по серийному номеру
     *
     * @param serialNumber номер серии, по которому ищется жесткий диск
     * @return найденный диск
     */
    @GetMapping("/{serialNumber}")
    public HddDto getHddProduct(@PathVariable long serialNumber) {
        return mapToDto(hddService.getHddProduct(serialNumber));
    }

    /**
     * Возвращает все товары типа жесткий диск
     *
     * @return список из товаров
     */
    @GetMapping
    public List<HddDto> getAllHddProducts() {
        return hddService.getAllHddProducts()
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
    public HddDto addHddProduct(@RequestBody HddDto dto) {
        return mapToDto(hddService.addHddProduct(mapToHdd(dto)));
    }

    /**
     * Метод редактирования продукта
     *
     * @param dto тело запроса для изменения
     * @return измененный продукт
     */
    @PutMapping
    public HddDto updateHddProduct(@RequestBody HddDto dto) {
        return mapToDto(hddService.updateHddProduct(mapToHdd(dto)));
    }

    private HDD mapToHdd(HddDto dto) {
        return mapper.map(dto, HDD.class);
    }

    private HddDto mapToDto(HDD hdd) {
        return mapper.map(hdd, HddDto.class);
    }
}
