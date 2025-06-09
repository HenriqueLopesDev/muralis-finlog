package org.finlog.finlogbackendspring.business.paymentType.domain.gateway;

import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;

import java.util.List;

public interface PaymentTypeGateway {

    List<PaymentType> findAllPaymentTypes();
    PaymentType findPaymentTypeById(Long id);
}
