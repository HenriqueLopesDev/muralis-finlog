package org.finlog.finlogbackendspring.business.paymentType.application.service;

import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.business.paymentType.domain.useCase.FindAllPaymentTypes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationService {

    private final FindAllPaymentTypes findAllPaymentTypes;

    public QuotationService(FindAllPaymentTypes findAllPaymentTypes) {
        this.findAllPaymentTypes = findAllPaymentTypes;
    }

    public List<PaymentType> getAllPaymentTypes() {
        return this.findAllPaymentTypes.execute();
    }
}
