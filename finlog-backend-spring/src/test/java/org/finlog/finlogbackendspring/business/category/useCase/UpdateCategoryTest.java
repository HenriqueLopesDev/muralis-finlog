package org.finlog.finlogbackendspring.business.category.useCase;

import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.category.domain.gateway.CategoryGateway;
import org.finlog.finlogbackendspring.business.category.domain.useCase.UpdateCategory;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UpdateCategoryTest {

    private CategoryGateway categoryGateway;
    private UseCase<Category, Void> updateCategory;
    private Category mockCategory;

    @BeforeEach
    void setUp() {
        this.categoryGateway = mock(CategoryGateway.class);
        this.updateCategory = new UpdateCategory(this.categoryGateway);
        this.mockCategory = new Category();
    }

    @Test
    void shouldUpdateCategorySuccessfully() {

        doNothing().when(this.categoryGateway).updateCategory(this.mockCategory);

        this.updateCategory.execute(this.mockCategory);

        verify(this.categoryGateway, times(1)).updateCategory(this.mockCategory);
    }

    @Test
    void shouldThrowExceptionWhenGatewayFails() {

        doThrow(mock(DataAccessException.class))
                .when(this.categoryGateway).updateCategory(this.mockCategory);

        assertThrows(DataAccessException.class, () -> this.updateCategory.execute(this.mockCategory));

        verify(this.categoryGateway, times(1)).updateCategory(this.mockCategory);
    }
}
