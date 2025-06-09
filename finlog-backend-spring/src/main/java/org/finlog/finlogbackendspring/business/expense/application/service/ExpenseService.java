package org.finlog.finlogbackendspring.business.expense.application.service;

import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.mapper.CreateExpenseDtoToExpense;
import org.finlog.finlogbackendspring.business.expense.domain.mapper.ExpenseToExpenseList;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.DeleteExpense;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.FindAllExpenses;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.SaveExpense;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseCreateRequest;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.response.ExpenseListDto;
import org.finlog.finlogbackendspring.config.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseService {

    private final FindAllExpenses findAllExpenses;
    private final DeleteExpense deleteExpense;
    private final SaveExpense saveExpense;
    private final Mapper<ExpenseCreateRequest, Expense> createExpenseDtoExpense;

    public ExpenseService(FindAllExpenses findAllExpenses, DeleteExpense deleteExpense, SaveExpense saveExpense, CreateExpenseDtoToExpense createExpenseDtoToExpense) {
        this.findAllExpenses = findAllExpenses;
        this.deleteExpense = deleteExpense;
        this.saveExpense = saveExpense;
        this.createExpenseDtoExpense = createExpenseDtoToExpense;
    }

    public List<ExpenseListDto> getAllExpenses() {
        return findAllExpenses.execute().stream().map(new ExpenseToExpenseList()::map).toList();
    }

    public void deleteExpense(Long id) {
        this.deleteExpense.execute(id);
    }

    @Transactional
    public Long createExpense(ExpenseCreateRequest expenseCreateRequest) {
        return this.saveExpense.execute(this.createExpenseDtoExpense.map(expenseCreateRequest));
    }
}
