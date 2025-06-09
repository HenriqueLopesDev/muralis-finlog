package org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.category.infrastructure.dto.CategorySummaryDto;
import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseListDto (
    Long id,
    String description,
    BigDecimal value,
    LocalDateTime date,
    PaymentType paymentType,
    @JsonProperty("category") CategorySummaryDto categorySummaryDto,
    Address address
){}
