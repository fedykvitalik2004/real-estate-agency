package org.vitalii.fedyk.realestateagency.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.vitalii.fedyk.realestateagency.enumeration.Option;
import org.vitalii.fedyk.realestateagency.enumeration.PropertyType;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Document(value = "properties")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Property {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private PropertyType type;
    private Option option;
    private double price;
    private int bedrooms;
    private int bathrooms;
    private int area;
    private Address address;
    private String description;
    private Long realtorId;
    private LocalDateTime createdAt = LocalDateTime.now();
    private ZonedDateTime updatedAt;
}
