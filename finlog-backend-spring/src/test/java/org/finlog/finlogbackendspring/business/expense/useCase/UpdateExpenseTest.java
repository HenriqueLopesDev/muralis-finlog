package org.finlog.finlogbackendspring.business.expense.useCase;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.domain.useCase.UpdateAddress;
import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.category.domain.useCase.UpdateCategory;
import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.FindExpenseById;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.UpdateExpense;
import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UpdateExpenseTest {

    private ExpenseGateway expenseGateway;
    private FindExpenseById findExpenseById;
    private UseCase<Expense, Void> updateExpense;
    private Expense mockExpense;
    private final long DEFAULT_ID = 1L;

    @BeforeEach
    void setUp() {
        this.expenseGateway = mock(ExpenseGateway.class);
        this.findExpenseById = mock(FindExpenseById.class);
        UpdateCategory updateCategory = mock(UpdateCategory.class);
        UpdateAddress updateAddress = mock(UpdateAddress.class);

        this.updateExpense = new UpdateExpense(
                this.expenseGateway,
                this.findExpenseById,
                updateCategory,
                updateAddress
        );

        this.mockExpense = new Expense();
        this.mockExpense.setId(DEFAULT_ID);
        this.mockExpense.setDescription("any description");
        this.mockExpense.setValue(BigDecimal.valueOf(100.0));
        this.mockExpense.setDate(LocalDate.parse("2024-01-01"));
        this.mockExpense.setActive(true);
        this.mockExpense.setCategory(new Category());
        this.mockExpense.setPaymentType(new PaymentType());
        this.mockExpense.setAddress(new Address());
    }

    @Test
    void shouldCallUpdateExpense() {
        when(findExpenseById.execute(DEFAULT_ID)).thenReturn(this.mockExpense);

        this.updateExpense.execute(this.mockExpense);

        verify(this.expenseGateway, times(1)).updateExpense(this.mockExpense);
    }

    @Test
    void shouldThrowExceptionWhenGatewayFails() {
        when(findExpenseById.execute(DEFAULT_ID)).thenReturn(this.mockExpense);
        doThrow(mock(DataAccessException.class))
                .when(this.expenseGateway).updateExpense(this.mockExpense);

        assertThrows(DataAccessException.class, () -> this.updateExpense.execute(this.mockExpense));

        verify(this.expenseGateway, times(1)).updateExpense(this.mockExpense);
    }
}
