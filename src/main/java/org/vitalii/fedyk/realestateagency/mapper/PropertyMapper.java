package org.vitalii.fedyk.realestateagency.mapper;

import org.vitalii.fedyk.realestateagency.dto.AddressDto;
import org.vitalii.fedyk.realestateagency.dto.OnePropertyDtoResponse;
import org.vitalii.fedyk.realestateagency.dto.PropertyDtoResponse;
import org.vitalii.fedyk.realestateagency.dto.PropertySaveDto;
import org.vitalii.fedyk.realestateagency.entity.Address;
import org.vitalii.fedyk.realestateagency.entity.Property;
import org.vitalii.fedyk.realestateagency.entity.Realtor;

public class PropertyMapper {
    public static Property toEntity(final PropertySaveDto dto) {
        final Property property = new Property();
        property.setType(dto.getType());
        property.setOption(dto.getOption());
        property.setPrice(dto.getPrice());
        property.setBedrooms(dto.getBedrooms());
        property.setBathrooms(dto.getBathrooms());
        property.setArea(dto.getArea());
        property.setAddress(AddressMapper.toEntity(dto.getAddressDto()));
        property.setDescription(dto.getDescription());
        property.setRealtorId(dto.getRealtorId());
        return property;
    }

    public static PropertyDtoResponse toDto(final Property property) {
        return PropertyDtoResponse.builder()
                .id(property.getId())
                .type(property.getType())
                .option(property.getOption())
                .price(property.getPrice())
                .area(property.getArea())
                .addressDto(AddressMapper.toDto(property.getAddress()))
                .description(property.getDescription())
                .realtorId(property.getRealtorId())
                .createdAt(property.getCreatedAt())
                .build();
    }

    public static OnePropertyDtoResponse toOnePropertyDtoResponse(final Property property, final Realtor realtor) {
        return OnePropertyDtoResponse.builder()
                .id(property.getId())
                .type(property.getType())
                .option(property.getOption())
                .price(property.getPrice())
                .bedrooms(property.getBedrooms())
                .bathrooms(property.getBathrooms())
                .area(property.getArea())
                .addressDto(AddressMapper.toDto(property.getAddress()))
                .description(property.getDescription())
                .realtorId(property.getRealtorId())
                .phoneNumber(realtor.getPhoneNumber())
                .createdAt(property.getCreatedAt())
                .build();
    }

    public static class AddressMapper {
        public static Address toEntity(final AddressDto dto) {
            Address address = new Address();
            address.setStreet(dto.getStreet());
            address.setCity(dto.getCity());
            address.setState(dto.getState());
            address.setPostalCode(dto.getPostalCode());
            address.setCountry(dto.getCountry());
            return address;
        }

        public static AddressDto toDto(final Address address) {
            return AddressDto.builder()
                    .street(address.getStreet())
                    .city(address.getCity())
                    .state(address.getState())
                    .postalCode(address.getPostalCode())
                    .country(address.getCountry())
                    .build();
        }
    }
}
