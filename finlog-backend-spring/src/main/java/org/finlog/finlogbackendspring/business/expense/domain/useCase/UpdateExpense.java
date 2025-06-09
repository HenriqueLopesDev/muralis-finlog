package org.finlog.finlogbackendspring.business.expense.domain.useCase;

import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.springframework.stereotype.Component;

@Component
public class UpdateExpense {

    private final ExpenseGateway expenseGateway;

    public UpdateExpense(ExpenseGateway expenseGateway) {
        this.expenseGateway = expenseGateway;
    }

    public void execute(Expense expense) {
        this.expenseGateway.updateExpense(expense);
    }
}
