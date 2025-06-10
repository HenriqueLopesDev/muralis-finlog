package org.finlog.finlogbackendspring.business.expense.domain.useCase;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.domain.useCase.UpdateAddress;
import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.category.domain.useCase.UpdateCategory;
import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.springframework.stereotype.Component;

@Component
public class UpdateExpense implements UseCase<Expense, Void> {

    private final ExpenseGateway expenseGateway;
    private final UseCase<Long, Expense> findExpenseById;
    private final UseCase<Category, Void> updateCategory;
    private final UseCase<Address, Void> updateAddress;

    public UpdateExpense(ExpenseGateway expenseGateway, FindExpenseById findExpenseById, UpdateCategory updateCategory, UpdateAddress updateAddress) {
        this.expenseGateway = expenseGateway;
        this.findExpenseById = findExpenseById;
        this.updateCategory = updateCategory;
        this.updateAddress = updateAddress;
    }

    public Void execute(Expense expenseDataToBeUpdated) {
        Expense foundExpense = this.findExpenseById.execute(expenseDataToBeUpdated.getId());

        foundExpense.setDescription(expenseDataToBeUpdated.getDescription());
        foundExpense.setValue(expenseDataToBeUpdated.getValue());
        foundExpense.setDate(expenseDataToBeUpdated.getDate());
        foundExpense.setActive(expenseDataToBeUpdated.getActive());

        foundExpense.getCategory().setName(expenseDataToBeUpdated.getCategory().getName());
        foundExpense.getCategory().setDescription(expenseDataToBeUpdated.getCategory().getDescription());

        foundExpense.setPaymentType(expenseDataToBeUpdated.getPaymentType());

        foundExpense.getAddress().setCity(expenseDataToBeUpdated.getAddress().getCity());
        foundExpense.getAddress().setNeighborhood(expenseDataToBeUpdated.getAddress().getNeighborhood());
        foundExpense.getAddress().setState(expenseDataToBeUpdated.getAddress().getState());
        foundExpense.getAddress().setStreet(expenseDataToBeUpdated.getAddress().getStreet());
        foundExpense.getAddress().setStreetNumber(expenseDataToBeUpdated.getAddress().getStreetNumber());
        foundExpense.getAddress().setZipCode(expenseDataToBeUpdated.getAddress().getZipCode());
        foundExpense.getAddress().setComplement(expenseDataToBeUpdated.getAddress().getComplement().orElse(null));

        this.updateCategory.execute(foundExpense.getCategory());
        this.updateAddress.execute(foundExpense.getAddress());
        this.expenseGateway.updateExpense(foundExpense);
        return null;
    }
}
