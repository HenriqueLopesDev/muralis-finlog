package org.finlog.finlogbackendspring.business.expense.useCase;

import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.UpdateExpense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UpdateExpenseTest {

    private ExpenseGateway expenseGateway;
    private UpdateExpense updateExpense;
    private final long DEFAULT_ID = 1L;

    @BeforeEach
    void setUp() {
        this.expenseGateway = mock(ExpenseGateway.class);
        this.updateExpense = new UpdateExpense(this.expenseGateway);
    }

    @Test
    void shouldCallUpdateExpense() {
        Expense mockExpense = new Expense();
        mockExpense.setId(DEFAULT_ID);

        this.updateExpense.execute(mockExpense);

        verify(this.expenseGateway, times(1)).updateExpense(mockExpense);
    }

    @Test
    void shouldThrowExceptionWhenGatewayFails() {
        Expense mockExpense = new Expense();
        mockExpense.setId(DEFAULT_ID);

        doThrow(mock(DataAccessException.class))
                .when(this.expenseGateway).updateExpense(mockExpense);

        assertThrows(DataAccessException.class, () -> this.updateExpense.execute(mockExpense));
        verify(this.expenseGateway, times(1)).updateExpense(mockExpense);
    }
}
