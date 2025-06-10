package org.finlog.finlogbackendspring.business.expense.useCase;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.FindAllExpenses;
import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.config.pagination.PaginatedResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FindAllExpensesTest {

    private ExpenseGateway expenseGateway;
    private FindAllExpenses findAllExpenses;

    @BeforeEach
    void setUp() {
        this.expenseGateway = mock(ExpenseGateway.class);
        this.findAllExpenses = new FindAllExpenses(expenseGateway);
    }

    @Test
    void shouldReturnPaginatedExpenses() {
        Expense expense = new Expense();

        List<Expense> expenses = List.of(expense);

        PaginatedResult<Expense> paginated = new PaginatedResult<>(expenses, 1, 1, 1);

        when(this.expenseGateway.findAllExpenses(1)).thenReturn(paginated);

        PaginatedResult<Expense> result = this.findAllExpenses.execute(1);

        assertNotNull(result);
        assertEquals(1, result.content().size());
        assertEquals(1, result.currentPage());
        assertEquals(1, result.totalPages());
        assertEquals(1, result.totalItems());

        verify(this.expenseGateway, times(1)).findAllExpenses(1);
    }

    @Test
    void shouldReturnEmptyPaginatedResultWhenNoExpensesFound() {
        PaginatedResult<Expense> emptyResult = new PaginatedResult<>(List.of(), 1, 0, 0);

        when(this.expenseGateway.findAllExpenses(1)).thenReturn(emptyResult);

        PaginatedResult<Expense> result = this.findAllExpenses.execute(1);

        assertNotNull(result);
        assertTrue(result.content().isEmpty());
        assertEquals(0, result.totalItems());
        assertEquals(0, result.totalPages());
        assertEquals(1, result.currentPage());

        verify(this.expenseGateway, times(1)).findAllExpenses(1);
    }

}
