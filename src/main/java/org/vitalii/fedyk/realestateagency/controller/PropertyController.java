package org.vitalii.fedyk.realestateagency.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vitalii.fedyk.realestateagency.dto.OnePropertyDtoResponse;
import org.vitalii.fedyk.realestateagency.dto.PropertyDtoResponse;
import org.vitalii.fedyk.realestateagency.dto.PropertySaveDto;
import org.vitalii.fedyk.realestateagency.dto.PropertyUpdateDto;
import org.vitalii.fedyk.realestateagency.service.PropertyService;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@AllArgsConstructor
public class PropertyController {
    private PropertyService propertyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyDtoResponse create(@RequestBody @Valid PropertySaveDto propertySaveDto) {
        return propertyService.save(propertySaveDto);
    }

    @GetMapping("/{id}")
    public OnePropertyDtoResponse getById(@PathVariable("id") String id) {
        return propertyService.findById(id);
    }

    @PutMapping("/{id}")
    public PropertyDtoResponse update(@PathVariable("id") String id, @RequestBody @Valid PropertyUpdateDto propertySaveDto) {
        return propertyService.update(id, propertySaveDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@RequestParam("id") List<String> id) {
        propertyService.deleteById(id);
    }

    @GetMapping
    public List<PropertyDtoResponse> getAllByRealtorId(@RequestParam("realtorId") Long realtorId) {
        return propertyService.findAllByRealtorId(realtorId);
    }

    @GetMapping("/all")
    public Page<PropertyDtoResponse> getAll(Pageable pageable) {
        return propertyService.findAll(pageable);
    }
}
