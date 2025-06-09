package org.finlog.finlogbackendspring.business.expense.useCase;

import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.exception.ExpenseNotFoundException;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.DeleteExpense;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.FindExpenseById;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.UpdateExpense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteExpenseTest {

    private FindExpenseById findExpenseById;
    private UpdateExpense updateExpense;
    private DeleteExpense deleteExpense;
    private final long DEFAULT_ID = 1L;

    @BeforeEach
    void setUp() {
        this.findExpenseById = mock(FindExpenseById.class);
        this.updateExpense = mock(UpdateExpense.class);
        this.deleteExpense = new DeleteExpense(this.findExpenseById, this.updateExpense);
    }

    @Test
    void shouldSetExpenseAsInactiveAndCallUpdate() {
        Expense mockExpense = new Expense();
        mockExpense.setId(DEFAULT_ID);
        mockExpense.setActive(true);

        when(this.findExpenseById.execute(DEFAULT_ID)).thenReturn(mockExpense);

        this.deleteExpense.execute(DEFAULT_ID);

        assertFalse(mockExpense.getActive());
        verify(this.findExpenseById, times(1)).execute(DEFAULT_ID);
        verify(this.updateExpense, times(1)).execute(mockExpense);
    }

    @Test
    void shouldPropagateExceptionIfFindFails() {
        when(this.findExpenseById.execute(DEFAULT_ID)).thenThrow(new ExpenseNotFoundException(DEFAULT_ID));

        assertThrows(ExpenseNotFoundException.class, () -> this.deleteExpense.execute(DEFAULT_ID));
        verify(this.updateExpense, never()).execute(any());
    }
}
