package org.vitalii.fedyk.realestateagency.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.vitalii.fedyk.realestateagency.entity.Property;
import org.vitalii.fedyk.realestateagency.repository.PropertyRepository;
import org.vitalii.fedyk.realestateagency.service.SecurityService;

import java.util.List;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private PropertyRepository propertyRepository;

    @Override
    public boolean arePropertiesAppertainedToRealtor(long realtorId, List<String> propertyId) {
        List<Property> properties = propertyRepository.findAllById(propertyId);
        if (properties.size() != propertyId.size()) {
            return false;
        }
        for (Property property : properties) {
            if (property.getRealtorId() == null || property.getRealtorId() != realtorId) {
                return false;
            }
        }
        return true;
    }
}
