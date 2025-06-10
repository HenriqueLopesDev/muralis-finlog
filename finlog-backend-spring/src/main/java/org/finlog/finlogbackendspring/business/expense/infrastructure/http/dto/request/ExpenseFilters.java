package org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request;

public record ExpenseFilters(
    int page,
    String startDate,
    String endDate
) {
}
