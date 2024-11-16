package org.vitalii.fedyk.realestateagency.service;

import java.util.List;

public interface SecurityService {
    boolean arePropertiesAppertainedToRealtor(long realtorId, List<String> propertyId);
}
