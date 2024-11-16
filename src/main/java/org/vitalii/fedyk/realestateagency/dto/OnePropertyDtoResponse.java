package org.vitalii.fedyk.realestateagency.dto;

import lombok.*;
import org.vitalii.fedyk.realestateagency.entity.Address;
import org.vitalii.fedyk.realestateagency.enumeration.Option;
import org.vitalii.fedyk.realestateagency.enumeration.PropertyType;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OnePropertyDtoResponse {
    private String id;
    private PropertyType type;
    private Option option;
    private double price;
    private int bedrooms;
    private int bathrooms;
    private int area;
    private AddressDto addressDto;
    private String description;
    private Long realtorId;
    private String phoneNumber;
    private LocalDateTime createdAt;
}
