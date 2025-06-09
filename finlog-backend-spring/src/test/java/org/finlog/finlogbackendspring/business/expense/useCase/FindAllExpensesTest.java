package org.finlog.finlogbackendspring.business.expense.useCase;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.FindAllExpenses;
import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
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
    void shouldReturnListOfExpenses() {

        PaymentType paymentType = new PaymentType(1L, "Pix");
        Category category = new Category(2L, "Transport", "Fuel");
        Address address = new Address(
                3L,
                "00000000",
                "SP",
                "São Paulo",
                "Neighborhood one",
                "Street one",
                "123",
                "Complement one"
        );

        List<Expense> expenses = List.of(
                new Expense(1L, "Gas Station", BigDecimal.valueOf(100.00), LocalDateTime.now(), paymentType, category, address, true),
                new Expense(2L, "Uber Ride", BigDecimal.valueOf(50.00), LocalDateTime.now(), paymentType, category, address, true)
        );

        when(this.expenseGateway.findAllExpenses()).thenReturn(expenses);

        List<Expense> result = findAllExpenses.execute();

        assertEquals(2, result.size());
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(this.expenseGateway, times(1)).findAllExpenses();
    }

    @Test
    void shouldReturnEmptyListWhenNoExpensesFound() {
        when(this.expenseGateway.findAllExpenses()).thenReturn(List.of());

        List<Expense> result = findAllExpenses.execute();

        assertTrue(result.isEmpty());
        verify(this.expenseGateway, times(1)).findAllExpenses();
    }

}
