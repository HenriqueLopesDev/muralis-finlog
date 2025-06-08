package org.finlog.finlogbackendspring.business.expense.domain.gateway;

import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;

import java.util.List;

public interface ExpenseGateway {

    List<Expense> findAllExpenses();
}
