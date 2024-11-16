package org.vitalii.fedyk.realestateagency.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LikePropertyDto {
    @NotNull(message = "exception_id_required")
    @Positive(message = "exception_id_incorrect")
    private Long id;
    @NotBlank(message = "exception_id_required")
    private String propertyId;
}
