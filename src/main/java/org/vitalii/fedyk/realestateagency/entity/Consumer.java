package org.vitalii.fedyk.realestateagency.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@DiscriminatorValue(value = "CONSUMER")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
public class Consumer extends User {
    @ElementCollection
    @CollectionTable(name = "saved_properties",
            joinColumns = @JoinColumn(name = "consumer_id"))
    @Column(name = "property_id")
    private List<String> savedProperties;
}
