package org.finlog.finlogbackendspring.business.expense.application.service;

import org.finlog.finlogbackendspring.business.expense.domain.mapper.ExpenseMapper;
import org.finlog.finlogbackendspring.business.expense.domain.useCase.FindAllExpenses;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.ExpenseListDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final FindAllExpenses findAllExpenses;

    public ExpenseService(FindAllExpenses findAllExpenses) {
        this.findAllExpenses = findAllExpenses;
    }

    public List<ExpenseListDto> getAllExpenses() {
        return findAllExpenses.execute().stream().map(ExpenseMapper::toDto).toList();
    }
}
