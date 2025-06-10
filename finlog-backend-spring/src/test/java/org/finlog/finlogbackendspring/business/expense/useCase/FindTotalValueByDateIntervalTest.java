package org.finlog.finlogbackendspring.business.expense.useCase;

import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.FindTotalValueByDateInterval;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseTotalValueRequest;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FindTotalValueByDateIntervalTest {

    private UseCase<ExpenseTotalValueRequest, BigDecimal> findTotalValueByDateIntervalUseCase;
    private ExpenseGateway expenseGateway;
    private ExpenseTotalValueRequest expenseTotalValueRequest;

    @BeforeEach
    void setUp() {
        this.expenseGateway = mock(ExpenseGateway.class);
        this.findTotalValueByDateIntervalUseCase = new FindTotalValueByDateInterval(expenseGateway);
        String START_DATE = "2024-01-01";
        String END_DATE = "2024-01-31";
        this.expenseTotalValueRequest = new ExpenseTotalValueRequest(START_DATE, END_DATE);
    }

    @Test
    void shouldReturnTotalValueSuccessfully() {

        BigDecimal expectedTotal = BigDecimal.valueOf(1234.56);

        when(expenseGateway.getTotalExpense(this.expenseTotalValueRequest)).thenReturn(expectedTotal);

        BigDecimal result = findTotalValueByDateIntervalUseCase.execute(this.expenseTotalValueRequest);

        assertEquals(expectedTotal, result);
        verify(expenseGateway, times(1)).getTotalExpense(this.expenseTotalValueRequest);
    }

    @Test
    void shouldThrowExceptionWhenGatewayFails() {

        doThrow(mock(DataAccessException.class)).when(expenseGateway).getTotalExpense(this.expenseTotalValueRequest);

        assertThrows(DataAccessException.class, () -> findTotalValueByDateIntervalUseCase.execute(this.expenseTotalValueRequest));
        verify(expenseGateway, times(1)).getTotalExpense(this.expenseTotalValueRequest);
    }
}
