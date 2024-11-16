package org.vitalii.fedyk.realestateagency.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vitalii.fedyk.realestateagency.dto.OnePropertyDtoResponse;
import org.vitalii.fedyk.realestateagency.dto.PropertyDtoResponse;
import org.vitalii.fedyk.realestateagency.dto.PropertySaveDto;
import org.vitalii.fedyk.realestateagency.dto.PropertyUpdateDto;
import org.vitalii.fedyk.realestateagency.entity.Property;
import org.vitalii.fedyk.realestateagency.entity.Realtor;
import org.vitalii.fedyk.realestateagency.exception.NotFoundException;
import org.vitalii.fedyk.realestateagency.mapper.PropertyMapper;
import org.vitalii.fedyk.realestateagency.repository.ConsumerRepository;
import org.vitalii.fedyk.realestateagency.repository.PropertyRepository;
import org.vitalii.fedyk.realestateagency.repository.RealtorRepository;
import org.vitalii.fedyk.realestateagency.service.PropertyService;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PropertyServiceImpl implements PropertyService {
    private ConsumerRepository consumerRepository;
    private PropertyRepository propertyRepository;
    private RealtorRepository realtorRepository;

    @Override
    public PropertyDtoResponse save(final PropertySaveDto propertySaveDto) {
        final boolean realtorExists = realtorRepository.existsById(propertySaveDto.getRealtorId());
        if (!realtorExists) throw new NotFoundException("exception_user_not_found");
        final Property property = PropertyMapper.toEntity(propertySaveDto);
        return PropertyMapper.toDto(propertyRepository.save(property));
    }

    @Override
    public OnePropertyDtoResponse findById(final String id) {
        final Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("exception_property_not_found"));
        final Realtor realtor = realtorRepository.findById(property.getRealtorId())
                .orElseThrow(() -> new NotFoundException("exception_realtor_not_found"));
        return PropertyMapper.toOnePropertyDtoResponse(property, realtor);
    }

    @Override
    @PreAuthorize("@securityServiceImpl.arePropertiesAppertainedToRealtor( authentication.principal.id, #id)")
    public PropertyDtoResponse update(final String id, final PropertyUpdateDto propertyUpdateDto) {
        final Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("exception_property_not_found"));
        property.setType(propertyUpdateDto.getType());
        property.setOption(propertyUpdateDto.getOption());
        property.setPrice(propertyUpdateDto.getPrice());
        property.setBedrooms(propertyUpdateDto.getBedrooms());
        property.setBathrooms(propertyUpdateDto.getBathrooms());
        property.setArea(propertyUpdateDto.getArea());
        property.setAddress(PropertyMapper.AddressMapper.toEntity(propertyUpdateDto.getAddressDto()));
        property.setDescription(propertyUpdateDto.getDescription());
        propertyRepository.save(property);
        return PropertyMapper.toDto(property);
    }

    @Override
    @PreAuthorize("@securityServiceImpl.arePropertiesAppertainedToRealtor( authentication.principal.id, #id)")
    public void deleteById(final List<String> id) {
        propertyRepository.deleteAllById(id);
        consumerRepository.deletePropertyById(id);
    }

    @Override
    public List<PropertyDtoResponse> findAllByRealtorId(final long realtorId) {
        final List<Property> properties = propertyRepository.findAllByRealtorId(realtorId);
        return properties.stream()
                .map(PropertyMapper::toDto)
                .toList();
    }

    @Override
    public Page<PropertyDtoResponse> findAll(final Pageable pageable) {
        return propertyRepository.findAll(pageable)
                .map(PropertyMapper::toDto);
    }
}
