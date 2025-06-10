package org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request;

public record ExpenseTotalValueRequest(
    String startDate,
    String endDate
) {}
