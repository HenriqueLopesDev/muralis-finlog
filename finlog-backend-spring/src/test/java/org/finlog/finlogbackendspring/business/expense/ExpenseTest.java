package org.finlog.finlogbackendspring.business.expense;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {

    private static final Long DEFAULT_ID = 99L;
    private static final String DEFAULT_DESCRIPTION = "Gasoline";
    private static final BigDecimal DEFAULT_VALUE = BigDecimal.valueOf(50.00);
    private static final LocalDateTime DEFAULT_DATE = LocalDateTime.of(2025, 6, 7, 18, 30);

    private PaymentType paymentType;
    private Category category;
    private Address address;

    @BeforeEach
    void setup() {
        this.paymentType = new PaymentType(1L, "Pix");
        this.category = new Category(2L, "Food", "Restaurant expenses");
        this.address = new Address(
                3L,
                "00000000",
                "SP",
                "São Paulo",
                "Neighborhood one",
                "Street one",
                "123",
                "Complement one"
        );;
    }

    @Test
    void shouldSetAllFieldsCorrectlyWhenUsingSetters() {
        Expense expense = new Expense();

        expense.setId(DEFAULT_ID);
        expense.setDescription(DEFAULT_DESCRIPTION);
        expense.setValue(DEFAULT_VALUE);
        expense.setDate(DEFAULT_DATE);
        expense.setPaymentType(paymentType);
        expense.setCategory(category);
        expense.setAddress(address);

        assertEquals(DEFAULT_ID, expense.getId());
        assertEquals(DEFAULT_DESCRIPTION, expense.getDescription());
        assertEquals(DEFAULT_VALUE, expense.getValue());
        assertEquals(DEFAULT_DATE, expense.getDate());
        assertEquals(paymentType, expense.getPaymentType());
        assertEquals(category, expense.getCategory());
        assertEquals(address, expense.getAddress());
    }

    @Test
    void shouldInitializeAllFieldsCorrectlyWhenUsingAllArgsConstructor() {
        Expense expense = new Expense(
                DEFAULT_ID,
                DEFAULT_DESCRIPTION,
                DEFAULT_VALUE,
                DEFAULT_DATE,
                paymentType,
                category,
                address
        );

        assertEquals(DEFAULT_ID, expense.getId());
        assertEquals(DEFAULT_DESCRIPTION, expense.getDescription());
        assertEquals(DEFAULT_VALUE, expense.getValue());
        assertEquals(DEFAULT_DATE, expense.getDate());
        assertEquals(paymentType, expense.getPaymentType());
        assertEquals(category, expense.getCategory());
        assertEquals(address, expense.getAddress());
    }
}
