package org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ExpenseCreateCategoryRequest (
        @NotBlank(message = "Category name is required")
        @Size(max = 255)
        String name,

        @NotBlank(message = "Category description is required")
        @Size(max = 255)
        String description
){}
