package org.finlog.finlogbackendspring.business.expense.domain.useCase;

import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllExpenses {

    private final ExpenseGateway expenseGateway;

    public FindAllExpenses(ExpenseGateway expenseGateway) {
        this.expenseGateway = expenseGateway;
    }

    public List<Expense> execute() {
        return this.expenseGateway.findAllExpenses();
    }
}
