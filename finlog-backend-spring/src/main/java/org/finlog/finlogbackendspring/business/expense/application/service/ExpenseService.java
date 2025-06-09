package org.finlog.finlogbackendspring.business.expense.application.service;

import org.finlog.finlogbackendspring.business.expense.domain.mapper.ExpenseMapper;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.DeleteExpense;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.FindAllExpenses;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.ExpenseListDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final FindAllExpenses findAllExpenses;
    private final DeleteExpense deleteExpense;

    public ExpenseService(FindAllExpenses findAllExpenses, DeleteExpense deleteExpense) {
        this.findAllExpenses = findAllExpenses;
        this.deleteExpense = deleteExpense;
    }

    public List<ExpenseListDto> getAllExpenses() {
        return findAllExpenses.execute().stream().map(new ExpenseMapper()::toDto).toList();
    }

    public void deleteExpense(Long id) {
        this.deleteExpense.execute(id);
    }
}
