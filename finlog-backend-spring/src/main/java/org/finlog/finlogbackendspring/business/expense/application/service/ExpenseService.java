package org.finlog.finlogbackendspring.business.expense.application.service;

import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.mapper.CreateExpenseDtoToExpense;
import org.finlog.finlogbackendspring.business.expense.domain.mapper.ExpenseToExpenseList;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.*;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseFilters;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseCreateRequest;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseTotalValueRequest;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.response.ExpenseListDto;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.finlog.finlogbackendspring.config.pagination.PaginatedData;
import org.finlog.finlogbackendspring.config.domain.mapper.Mapper;
import org.finlog.finlogbackendspring.config.pagination.PaginatedResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {

    private final UseCase<ExpenseFilters, PaginatedResult<Expense>> findAllExpenses;
    private final UseCase<Long, Expense> findExpenseById;
    private final DeleteExpense deleteExpense;
    private final SaveExpense saveExpense;
    private final UseCase<Expense, Void> updateExpense;
    private final Mapper<ExpenseCreateRequest, Expense> createExpenseDtoExpense;
    private final UseCase<ExpenseTotalValueRequest, BigDecimal> findTotalValueByDateInterval;

    public ExpenseService(
            FindAllExpenses findAllExpenses,
            FindExpenseById findExpenseById,
            DeleteExpense deleteExpense,
            SaveExpense saveExpense,
            UpdateExpense updateExpense,
            CreateExpenseDtoToExpense createExpenseDtoToExpense,
            FindTotalValueByDateInterval findTotalValueByDateInterval
    ) {
        this.findAllExpenses = findAllExpenses;
        this.findExpenseById = findExpenseById;
        this.deleteExpense = deleteExpense;
        this.saveExpense = saveExpense;
        this.updateExpense = updateExpense;
        this.createExpenseDtoExpense = createExpenseDtoToExpense;
        this.findTotalValueByDateInterval = findTotalValueByDateInterval;
    }

    public PaginatedData<ExpenseListDto> getAllExpenses(ExpenseFilters expenseFilters) {
        PaginatedResult<Expense> expensePage = this.findAllExpenses.execute(expenseFilters);

        List<ExpenseListDto> mappedContent = expensePage.content().stream()
                .map(new ExpenseToExpenseList()::map)
                .toList();

        return new PaginatedData<>(
                mappedContent,
                expensePage.currentPage(),
                expensePage.totalPages(),
                expensePage.totalItems()
        );
    }

    public Expense getExpenseById(Long id) {
        return this.findExpenseById.execute(id);
    }

    public BigDecimal getTotalExpenses(String startDate, String endDate) {
        return this.findTotalValueByDateInterval.execute(new ExpenseTotalValueRequest(startDate, endDate));
    }

    @Transactional
    public void updateExpense(Long id, ExpenseCreateRequest expenseCreateRequest) {
        Expense expense = this.createExpenseDtoExpense.map(expenseCreateRequest);
        expense.setId(id);
        this.updateExpense.execute(expense);
    }

    public void deleteExpense(Long id) {
        this.deleteExpense.execute(id);
    }

    @Transactional
    public Long createExpense(ExpenseCreateRequest expenseCreateRequest) {
        return this.saveExpense.execute(this.createExpenseDtoExpense.map(expenseCreateRequest));
    }
}
