package org.finlog.finlogbackendspring.business.expense.domain.useCase;

import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.springframework.stereotype.Component;

@Component
public class DeleteExpense {

    private final FindExpenseById findExpenseById;
    private final UpdateExpense updateExpense;

    public DeleteExpense(FindExpenseById findExpenseById, UpdateExpense updateExpense) {
        this.findExpenseById = findExpenseById;
        this.updateExpense = updateExpense;
    }

    public void execute(Long id) {
        Expense foundExpense = findExpenseById.execute(id);
        foundExpense.setActive(false);
        this.updateExpense.execute(foundExpense);
    }
}
