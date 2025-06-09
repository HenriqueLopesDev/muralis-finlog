package org.finlog.finlogbackendspring.business.expense.domain.gateway;

import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;

import java.util.List;

public interface ExpenseGateway {

    List<Expense> findAllExpenses();
    Expense findExpenseById(Long id);
    void updateExpense(Expense expense);
    Long saveExpense(Expense expense);
}
