package org.finlog.finlogbackendspring.business.paymentType.domain.useCase;

import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.business.paymentType.domain.gateway.PaymentTypeGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllPaymentTypes {

    private final PaymentTypeGateway paymentTypeGateway;

    public FindAllPaymentTypes(PaymentTypeGateway paymentTypeGateway) {
        this.paymentTypeGateway = paymentTypeGateway;
    }


    public List<PaymentType> execute(){
        return this.paymentTypeGateway.findAllPaymentTypes();
    }
}
