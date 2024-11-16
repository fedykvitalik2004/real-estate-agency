package org.vitalii.fedyk.realestateagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.vitalii.fedyk.realestateagency.entity.Consumer;

import java.util.List;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    @Query(value = """
            DELETE FROM saved_properties
            WHERE saved_properties.property_id IN :propertyId
            """, nativeQuery = true)
    @Modifying
    void deletePropertyById(List<String> propertyId);
}
