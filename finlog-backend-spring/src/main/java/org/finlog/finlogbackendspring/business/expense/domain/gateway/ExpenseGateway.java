package org.finlog.finlogbackendspring.business.expense.domain.gateway;

import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseFilters;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseTotalValueRequest;
import org.finlog.finlogbackendspring.config.pagination.PaginatedResult;

import java.math.BigDecimal;

public interface ExpenseGateway {

    PaginatedResult<Expense> findAllExpenses(ExpenseFilters expenseFilters);
    Expense findExpenseById(Long id);
    void updateExpense(Expense expense);
    Long saveExpense(Expense expense);
    BigDecimal getTotalExpense(ExpenseTotalValueRequest expenseTotalValueRequest);
}
