package org.vitalii.fedyk.realestateagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vitalii.fedyk.realestateagency.dto.OnePropertyDtoResponse;
import org.vitalii.fedyk.realestateagency.dto.PropertyDtoResponse;
import org.vitalii.fedyk.realestateagency.dto.PropertySaveDto;
import org.vitalii.fedyk.realestateagency.dto.PropertyUpdateDto;

import java.util.List;

public interface PropertyService {
    PropertyDtoResponse save(PropertySaveDto propertySaveDto);

    OnePropertyDtoResponse findById(String id);

    PropertyDtoResponse update(String id, PropertyUpdateDto propertySaveDto);

    void deleteById(List<String> id);

    List<PropertyDtoResponse> findAllByRealtorId(long realtorId);

    Page<PropertyDtoResponse> findAll(Pageable pageable);
}
