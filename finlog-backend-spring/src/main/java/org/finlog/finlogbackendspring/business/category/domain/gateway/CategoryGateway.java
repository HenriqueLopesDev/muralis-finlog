package org.finlog.finlogbackendspring.business.category.domain.gateway;

import org.finlog.finlogbackendspring.business.category.domain.entity.Category;

public interface CategoryGateway {

    Category saveCategory(Category category);
    Void updateCategory(Category category);
}
