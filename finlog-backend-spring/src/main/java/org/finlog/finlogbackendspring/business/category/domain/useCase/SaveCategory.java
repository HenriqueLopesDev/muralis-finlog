package org.finlog.finlogbackendspring.business.category.domain.useCase;

import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.category.domain.gateway.CategoryGateway;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.springframework.stereotype.Component;

@Component
public class SaveCategory implements UseCase<Category, Category> {

    private final CategoryGateway categoryGateway;

    public SaveCategory(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public Category execute(Category category){
        return this.categoryGateway.saveCategory(category);
    }
}
