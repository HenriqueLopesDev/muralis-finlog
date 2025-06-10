package org.finlog.finlogbackendspring.business.expense.useCase;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.domain.useCase.SaveAddress;
import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.category.domain.useCase.SaveCategory;
import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.SaveExpense;
import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.business.paymentType.domain.useCase.FindPaymentTypeById;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SaveExpenseTest {
    private ExpenseGateway expenseGateway;
    private SaveCategory saveCategory;
    private FindPaymentTypeById findPaymentTypeById;
    private SaveAddress saveAddress;

    private SaveExpense saveExpense;

    private Expense expense;

    @BeforeEach
    void setUp() {
        this.expenseGateway = mock(ExpenseGateway.class);
        this.saveCategory = mock(SaveCategory.class);
        this.findPaymentTypeById = mock(FindPaymentTypeById.class);
        this.saveAddress = mock(SaveAddress.class);

        this.expense = this.buildExpense();

        this.saveExpense = new SaveExpense(
                this.expenseGateway,
                this.saveCategory,
                this.findPaymentTypeById,
                this.saveAddress
        );
    }

    @Test
    void shouldSaveExpenseSuccessfully() {

        Category category = this.expense.getCategory();
        PaymentType paymentType = this.expense.getPaymentType();
        Address address = this.expense.getAddress();

        when(saveCategory.execute(category)).thenReturn(category);
        when(findPaymentTypeById.execute(paymentType.getId())).thenReturn(paymentType);
        when(saveAddress.execute(address)).thenReturn(address);
        when(expenseGateway.saveExpense(expense)).thenReturn(1L);

        Long result = saveExpense.execute(expense);

        assertEquals(1L, result);
        verify(expenseGateway, times(1)).saveExpense(expense);
    }

    @Test
    void shouldThrowExceptionWhenGatewayFails() {
        Category category = this.expense.getCategory();
        PaymentType paymentType = this.expense.getPaymentType();
        Address address = this.expense.getAddress();

        when(saveCategory.execute(category)).thenReturn(category);
        when(findPaymentTypeById.execute(paymentType.getId())).thenReturn(paymentType);
        when(saveAddress.execute(address)).thenReturn(address);
        doThrow(mock(DataAccessException.class)).when(expenseGateway).saveExpense(expense);

        assertThrows(DataAccessException.class, () -> saveExpense.execute(expense));
        verify(expenseGateway, times(1)).saveExpense(expense);
    }

    private Expense buildExpense() {
        Category category = new Category();
        PaymentType paymentType = new PaymentType();
        Address address = new Address();

        Expense expense = new Expense();
        expense.setCategory(category);
        expense.setPaymentType(paymentType);
        expense.setAddress(address);

        return expense;
    }
}
