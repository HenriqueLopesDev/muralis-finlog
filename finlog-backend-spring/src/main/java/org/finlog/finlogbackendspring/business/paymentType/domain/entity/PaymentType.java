package org.finlog.finlogbackendspring.business.paymentType.domain.entity;

import org.finlog.finlogbackendspring.config.domain.entity.DomainEntity;

public class PaymentType extends DomainEntity {

    private String type;

    public PaymentType() {
    }

    public PaymentType(Long id, String type) {
        super(id);
        this.setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
