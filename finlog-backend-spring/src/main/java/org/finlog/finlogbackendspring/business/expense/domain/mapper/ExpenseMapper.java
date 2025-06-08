package org.finlog.finlogbackendspring.business.expense.domain.mapper;

import org.finlog.finlogbackendspring.business.category.infrastructure.dto.CategorySummaryDto;
import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.ExpenseListDto;
import org.finlog.finlogbackendspring.config.mapper.AbstractMapper;

public class ExpenseMapper extends AbstractMapper<Expense, ExpenseListDto> {

    @Override
    public ExpenseListDto toDto(Expense expense) {
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
