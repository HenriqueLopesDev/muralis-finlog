package org.finlog.finlogbackendspring.business.paymentType.domain.entity;

public class PaymentType {

    private Long id;

    private String type;

    public PaymentType() {
    }

    public PaymentType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
