package org.vitalii.fedyk.realestateagency.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RealtorRegisterRequest {
    @NotBlank(message = "exception_first_name_required")
    private String firstName;
    @NotBlank(message = "exception_last_name_required")
    private String lastName;
    @Past(message = "exception_date_of_birth_incorrect")
    @NotNull(message = "exception_date_of_birth_required")
    private ZonedDateTime dateOfBirth;
    @Email(message = "exception_email_form_incorrect")
    @NotBlank(message = "exception_email_required")
    private String email;
    @Size(min = 8, max = 16, message = "exception_password_size_incorrect")
    @NotBlank(message = "exception_password_required")
    private String password;
    @NotBlank(message = "exception_phone_number_required")
    private String phoneNumber;
}
