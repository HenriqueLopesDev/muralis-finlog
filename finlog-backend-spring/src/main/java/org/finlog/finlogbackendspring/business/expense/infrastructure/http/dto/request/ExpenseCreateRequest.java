package org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseCreateRequest(
        @NotBlank(message = "Description is required")
        @Size(max = 255, message = "Description must not exceed 255 characters")
        String description,

        @NotNull(message = "Value is required")
        @DecimalMin(value = "0.01", message = "Value must be greater than 0")
        BigDecimal value,

        @NotNull(message = "Date is required")
        @PastOrPresent(message = "Date must be in the past or present")
        LocalDateTime date,

        @NotNull(message = "Payment type ID is required")
        @Positive(message = "Payment type ID must be a positive number")
        Long paymentTypeId,

        @Valid
        @JsonProperty("category")
        ExpenseCreateCategoryRequest expenseCreateCategoryRequest,

        @Valid
        @JsonProperty("address")
        ExpenseCreateAddressRequest address
){}