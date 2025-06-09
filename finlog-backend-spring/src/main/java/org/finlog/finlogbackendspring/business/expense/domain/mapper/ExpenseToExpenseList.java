package org.finlog.finlogbackendspring.business.expense.domain.mapper;

import org.finlog.finlogbackendspring.business.category.infrastructure.dto.CategorySummaryDto;
import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.response.ExpenseListDto;
import org.finlog.finlogbackendspring.config.mapper.Mapper;

public class ExpenseToExpenseList implements Mapper<Expense, ExpenseListDto> {

    @Override
    public ExpenseListDto map(Expense expense) {
        return new ExpenseListDto(
            expense.getId(),
            expense.getDescription(),
            expense.getValue(),
            expense.getDate(),
            expense.getPaymentType(),
            this.buildCategorySummaryDto(expense),
            expense.getAddress()
        );
    }

    private CategorySummaryDto buildCategorySummaryDto(Expense expense) {
        return new CategorySummaryDto(expense.getCategory().getId(), expense.getCategory().getName());
    }
}
