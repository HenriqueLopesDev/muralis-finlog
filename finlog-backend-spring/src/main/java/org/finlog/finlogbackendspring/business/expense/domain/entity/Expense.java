package org.finlog.finlogbackendspring.business.expense.domain.entity;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.config.entity.DomainEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Expense extends DomainEntity {

    private String description;
    private BigDecimal value;
    private LocalDateTime date;
    private boolean active;
    private PaymentType paymentType;
    private Category category;
    private Address address;

    public Expense() {
    }

    public Expense(Long id, String description, BigDecimal value, LocalDateTime date, PaymentType paymentType, Category category, Address address, boolean active) {
        super(id);
        this.setDescription(description);
        this.setValue(value);
        this.setDate(date);
        this.setActive(active);
        this.setPaymentType(paymentType);
        this.setCategory(category);
        this.setAddress(address);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}