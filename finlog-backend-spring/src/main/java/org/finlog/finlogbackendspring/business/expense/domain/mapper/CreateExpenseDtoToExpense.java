package org.finlog.finlogbackendspring.business.expense.domain.mapper;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseCreateAddressRequest;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseCreateCategoryRequest;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseCreateRequest;
import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.config.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CreateExpenseDtoToExpense implements Mapper<ExpenseCreateRequest, Expense> {

    @Override
    public Expense map(ExpenseCreateRequest expenseCreateRequest) {
        Expense expense = new Expense();
        expense.setDescription(expenseCreateRequest.description());
        expense.setValue(expenseCreateRequest.value());
        expense.setDate(expenseCreateRequest.date());
        expense.setAddress(buildAddress(expenseCreateRequest));
        expense.setCategory(buildCategory(expenseCreateRequest));
        expense.setPaymentType(buildPaymentType(expenseCreateRequest));
        expense.setActive(true);
        return expense;
    }

    private Address buildAddress(ExpenseCreateRequest dto) {
        ExpenseCreateAddressRequest addressToBeCreated = dto.address();
        Address address = new Address();
        address.setStreet(addressToBeCreated.street());
        address.setCity(addressToBeCreated.city());
        address.setState(addressToBeCreated.state());
        address.setZipCode(addressToBeCreated.zipCode());
        address.setComplement(addressToBeCreated.complement());
        address.setStreetNumber(addressToBeCreated.streetNumber());
        address.setNeighborhood(addressToBeCreated.neighborhood());
        return address;
    }

    private Category buildCategory(ExpenseCreateRequest dto) {
        ExpenseCreateCategoryRequest categoryToBeCreated = dto.expenseCreateCategoryRequest();
        Category category = new Category();
        category.setName(categoryToBeCreated.name());
        category.setDescription(categoryToBeCreated.description());
        return category;
    }

    private PaymentType buildPaymentType(ExpenseCreateRequest dto) {
        PaymentType paymentType = new PaymentType();
        paymentType.setId(dto.paymentTypeId());
        return paymentType;
    }
}
