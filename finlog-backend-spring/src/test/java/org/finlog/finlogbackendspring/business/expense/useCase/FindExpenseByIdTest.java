package org.finlog.finlogbackendspring.business.expense.useCase;

import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.exception.ExpenseNotFoundException;
import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.FindExpenseById;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FindExpenseByIdTest {

    private ExpenseGateway expenseGateway;
    private FindExpenseById findExpenseById;
    private final long DEFAULT_ID = 1L;

    @BeforeEach
    void setUp() {
        this.expenseGateway = mock(ExpenseGateway.class);
        this.findExpenseById = new FindExpenseById(expenseGateway);
    }

    @Test
    void shouldReturnExpenseWhenFound() {
        Expense mockExpense = new Expense();
        mockExpense.setId(DEFAULT_ID);
        when(this.expenseGateway.findExpenseById(DEFAULT_ID)).thenReturn(mockExpense);

        Expense result = this.findExpenseById.execute(DEFAULT_ID);

        assertNotNull(result);
        assertEquals(DEFAULT_ID, result.getId());
        verify(this.expenseGateway, times(1)).findExpenseById(DEFAULT_ID);
    }

    @Test
    void shouldThrowExpenseNotFoundExceptionWhenDataAccessExceptionOccurs() {
        when(this.expenseGateway.findExpenseById(DEFAULT_ID))
                .thenThrow(mock(DataAccessException.class));

        ExpenseNotFoundException exception = assertThrows(
                ExpenseNotFoundException.class,
                () -> this.findExpenseById.execute(DEFAULT_ID)
        );

        assertEquals("Error finding expense by ID " + DEFAULT_ID, exception.getMessage());
        verify(this.expenseGateway, times(1)).findExpenseById(DEFAULT_ID);
    }
}
