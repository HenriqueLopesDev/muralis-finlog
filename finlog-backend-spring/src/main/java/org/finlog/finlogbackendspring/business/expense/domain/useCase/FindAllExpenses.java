package org.finlog.finlogbackendspring.business.expense.domain.useCase;

import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseFilters;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.finlog.finlogbackendspring.config.pagination.PaginatedResult;
import org.springframework.stereotype.Component;

@Component
public class FindAllExpenses implements UseCase<ExpenseFilters, PaginatedResult<Expense>> {

    private final ExpenseGateway expenseGateway;

    public FindAllExpenses(ExpenseGateway expenseGateway) {
        this.expenseGateway = expenseGateway;
    }

    public PaginatedResult<Expense> execute(ExpenseFilters expenseFilters) {
        return this.expenseGateway.findAllExpenses(expenseFilters);
    }
}
