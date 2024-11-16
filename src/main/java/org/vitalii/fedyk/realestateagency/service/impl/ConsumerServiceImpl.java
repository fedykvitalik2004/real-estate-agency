package org.vitalii.fedyk.realestateagency.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.vitalii.fedyk.realestateagency.entity.Consumer;
import org.vitalii.fedyk.realestateagency.repository.ConsumerRepository;
import org.vitalii.fedyk.realestateagency.service.ConsumerService;

@Service
@AllArgsConstructor
@Transactional
public class ConsumerServiceImpl implements ConsumerService {
    private ConsumerRepository consumerRepository;

    @Override
    @PreAuthorize("#id == authentication.principal.id")
    public boolean toggleLikeProperty(final long id, final String propertyId) {
        final Consumer customer = consumerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("exception_user_not_found"));
        if (customer.getSavedProperties().contains(propertyId)) {
            customer.getSavedProperties().remove(propertyId);
            return false;
        } else {
            customer.getSavedProperties().add(propertyId);
            return true;
        }
    }
}
