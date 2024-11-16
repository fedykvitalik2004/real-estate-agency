package org.vitalii.fedyk.realestateagency.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vitalii.fedyk.realestateagency.enumeration.Option;
import org.vitalii.fedyk.realestateagency.enumeration.PropertyType;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PropertyUpdateDto { //TODO: add validation
    private PropertyType type;
    private Option option;
    private Double price;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer area;
    @Valid
    private AddressDto addressDto;
    private String description;
}
