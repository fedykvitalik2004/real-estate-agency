package org.vitalii.fedyk.realestateagency.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(@Email(message = "exception_email_form_incorrect")
                                    @NotNull(message = "exception_email_required") String email,
                                    @Size(min = 8, max = 16, message = "exception_password_size_incorrect")
                                    @NotNull(message = "exception_password_required") String password) {
}
