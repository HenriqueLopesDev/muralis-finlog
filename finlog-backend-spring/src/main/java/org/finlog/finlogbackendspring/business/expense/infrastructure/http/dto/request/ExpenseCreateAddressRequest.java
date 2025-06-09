package org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ExpenseCreateAddressRequest(
        @NotBlank(message = "Street is required")
        @Size(max = 255, message = "Street must be at most 255 characters")
        String street,

        @NotBlank(message = "Street number is required")
        @Size(max = 255, message = "Street number must be at most 255 characters")
        String streetNumber,

        @NotBlank(message = "Neighborhood is required")
        @Size(max = 255, message = "Neighborhood must be at most 255 characters")
        String neighborhood,

        @NotBlank(message = "City is required")
        @Size(max = 255, message = "City must be at most 255 characters")
        String city,

        @NotBlank(message = "State is required")
        @Size(min = 2, max = 2, message = "State must be exactly 2 characters")
        String state,

        @NotBlank(message = "Zip code is required")
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "Zip code must be in the format XXXXX-XXX or XXXXXXXX")
        String zipCode,

        @Size(max = 255, message = "Complement must be at most 255 characters")
        String complement
){}
