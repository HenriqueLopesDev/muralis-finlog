package org.finlog.finlogbackendspring.business.expense.domain.mapper;

import org.finlog.finlogbackendspring.business.category.infrastructure.dto.CategorySummaryDto;
import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.ExpenseListDto;

public class ExpenseMapper {

    public static ExpenseListDto toDto(Expense expense) {
        return new ExpenseListDto(
            expense.getId(),
            expense.getDescription(),
            expense.getValue(),
            expense.getDate(),
            expense.getPaymentType(),
            new CategorySummaryDto(expense.getCategory().getId(), expense.getCategory().getName()),
            expense.getAddress()
        );
    }
}
