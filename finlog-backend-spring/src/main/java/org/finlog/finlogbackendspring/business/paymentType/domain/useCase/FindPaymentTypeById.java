package org.finlog.finlogbackendspring.business.paymentType.domain.useCase;

import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.business.paymentType.domain.exception.PaymentTypeNotFoundException;
import org.finlog.finlogbackendspring.business.paymentType.domain.gateway.PaymentTypeGateway;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.springframework.stereotype.Component;

@Component
public class FindPaymentTypeById implements UseCase<Long, PaymentType> {

    private final PaymentTypeGateway paymentTypeGateway;

    public FindPaymentTypeById(PaymentTypeGateway paymentTypeGateway) {
        this.paymentTypeGateway = paymentTypeGateway;
    }

    public PaymentType execute(Long paymentTypeId) {
        try{
            return paymentTypeGateway.findPaymentTypeById(paymentTypeId);
        } catch (Exception e) {
            throw new PaymentTypeNotFoundException(paymentTypeId);
        }
    }
}
