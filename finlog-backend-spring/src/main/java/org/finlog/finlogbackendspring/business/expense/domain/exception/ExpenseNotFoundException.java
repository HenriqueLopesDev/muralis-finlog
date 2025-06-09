package org.finlog.finlogbackendspring.business.expense.domain.exception;

import org.finlog.finlogbackendspring.config.http.exception.EntityNotFoundException;

public class ExpenseNotFoundException extends EntityNotFoundException {
    public ExpenseNotFoundException(Long idNotFound) {
        super("Error finding expense by ID " + idNotFound);
    }
}
