package org.finlog.finlogbackendspring.business.category.domain.useCase;

import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.category.domain.gateway.CategoryGateway;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.springframework.stereotype.Component;

@Component
public class UpdateCategory implements UseCase<Category, Void>{

    private final CategoryGateway categoryGateway;

    public UpdateCategory(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public Void execute(Category categoryDataToBeUpdated) {
        return this.categoryGateway.updateCategory(categoryDataToBeUpdated);
    }
}
