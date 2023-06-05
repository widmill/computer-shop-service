package com.example.computershopservice.controller;

import com.example.computershopservice.dto.MonitorDto;
import com.example.computershopservice.entity.Monitor;
import com.example.computershopservice.service.MonitorService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/monitors")
public class MonitorController {

    private final MonitorService monitorService;
    private final ModelMapper mapper;

    public MonitorController(MonitorService monitorService, ModelMapper mapper) {
        this.monitorService = monitorService;
        this.mapper = mapper;
    }

    /**
     * Возвращает все товары типа монитор
     *
     * @return список из товаров
     */
    @GetMapping
    public List<MonitorDto> getAllMonitorProducts() {

        return monitorService.getAllMonitorProducts()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Поиск монитора по серийному номеру
     *
     * @param serialNumber серийный номер, по которому идет поиск
     * @return Найденный продукт
     */
    @GetMapping("/{serialNumber}")
    public MonitorDto getMonitorProduct(@PathVariable Long serialNumber) {

        return mapToDto(monitorService.getMonitorProduct(serialNumber));
    }

    /**
     * Метод создания продукта
     *
     * @param dto тело запроса для создания
     * @return созданный продукт
     */
    @PostMapping
    public MonitorDto addMonitorProduct(@RequestBody MonitorDto dto) {

        return mapToDto(monitorService.addMonitorProduct(mapToMonitor(dto)));
    }

    /**
     * Метод редактирования продукта
     *
     * @param dto тело запроса для изменения
     * @return измененный продукт
     */
    @PutMapping
    public MonitorDto updateMonitorProduct(@RequestBody MonitorDto dto) {
        return mapToDto(monitorService.updateMonitorProduct(mapToMonitor(dto)));
    }

    private Monitor mapToMonitor(MonitorDto dto) {
        return mapper.map(dto, Monitor.class);
    }

    private MonitorDto mapToDto(Monitor monitor) {
        return mapper.map(monitor, MonitorDto.class);
    }
}
