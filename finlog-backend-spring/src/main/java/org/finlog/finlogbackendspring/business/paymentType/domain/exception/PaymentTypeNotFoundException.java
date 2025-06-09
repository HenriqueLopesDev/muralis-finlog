package org.finlog.finlogbackendspring.business.paymentType.domain.exception;

import org.finlog.finlogbackendspring.config.http.exception.EntityNotFoundException;

public class PaymentTypeNotFoundException extends EntityNotFoundException {
    public PaymentTypeNotFoundException(Long idNotFound) {
        super("Error finding payment type by ID " + idNotFound);
    }
}
