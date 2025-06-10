package org.finlog.finlogbackendspring.business.expense.domain.useCase;

import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.exception.ExpenseNotFoundException;
import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
public class FindExpenseById implements UseCase<Long, Expense> {

    private final ExpenseGateway expenseGateway;

    public FindExpenseById(ExpenseGateway expenseGateway) {
        this.expenseGateway = expenseGateway;
    }

    public Expense execute(Long id) {
        try {
            return expenseGateway.findExpenseById(id);
        } catch (DataAccessException ex) {
            throw new ExpenseNotFoundException(id);
        }

    }
}
