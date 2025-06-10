package org.finlog.finlogbackendspring.business.expense.domain.gateway;

import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.config.pagination.PaginatedResult;

public interface ExpenseGateway {

    PaginatedResult<Expense> findAllExpenses(int page);
    Expense findExpenseById(Long id);
    void updateExpense(Expense expense);
    Long saveExpense(Expense expense);
}
