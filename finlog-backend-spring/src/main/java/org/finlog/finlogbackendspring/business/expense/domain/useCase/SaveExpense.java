package org.finlog.finlogbackendspring.business.expense.domain.useCase;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.domain.useCase.SaveAddress;
import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.category.domain.useCase.SaveCategory;
import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.business.paymentType.domain.useCase.FindPaymentTypeById;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.springframework.stereotype.Component;

@Component
public class SaveExpense {

    private final ExpenseGateway expenseGateway;
    private final UseCase<Category, Category> saveCategory;
    private final UseCase<Long, PaymentType> findPaymentTypeById;
    private final UseCase<Address, Address> saveAddress;

    public SaveExpense(ExpenseGateway expenseGateway, SaveCategory saveCategory, FindPaymentTypeById findPaymentTypeById, SaveAddress saveAddress) {
            this.expenseGateway = expenseGateway;
            this.saveCategory = saveCategory;
            this.findPaymentTypeById = findPaymentTypeById;
            this.saveAddress = saveAddress;
    }

    public Long execute(Expense expense) {
        Category createdCategory = this.saveCategory.execute(expense.getCategory());
        PaymentType foundPaymentType = this.findPaymentTypeById.execute(expense.getPaymentType().getId());
        Address createdAddress = this.saveAddress.execute(expense.getAddress());

        expense.setPaymentType(foundPaymentType);
        expense.setCategory(createdCategory);
        expense.setAddress(createdAddress);

        return this.expenseGateway.saveExpense(expense);
    }
}
