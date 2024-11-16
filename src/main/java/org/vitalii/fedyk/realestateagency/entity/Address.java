package org.vitalii.fedyk.realestateagency.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Address {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
