package org.finlog.finlogbackendspring.business.expense.domain.useCase;

import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseTotalValueRequest;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FindTotalValueByDateInterval implements UseCase<ExpenseTotalValueRequest, BigDecimal> {

    private final ExpenseGateway expenseGateway;

    public FindTotalValueByDateInterval(ExpenseGateway expenseGateway) {
        this.expenseGateway = expenseGateway;
    }

    @Override
    public BigDecimal execute(ExpenseTotalValueRequest request) {
        return this.expenseGateway.getTotalExpense(request);
    }
}
