package org.vitalii.fedyk.realestateagency.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.vitalii.fedyk.realestateagency.entity.Property;

import java.util.List;

public interface PropertyRepository extends MongoRepository<Property, String> {
    List<Property> findAllByRealtorId(long realtorId);
}
