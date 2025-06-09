package org.finlog.finlogbackendspring.business.category.useCase;

import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.category.domain.gateway.CategoryGateway;
import org.finlog.finlogbackendspring.business.category.domain.useCase.SaveCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SaveCategoryTest {


    private CategoryGateway categoryGateway;
    private SaveCategory saveCategory;
    private Category category;

    @BeforeEach
    void setUp() {
        this.categoryGateway = mock(CategoryGateway.class);
        this.saveCategory = new SaveCategory(this.categoryGateway);
        this.category = new Category(1L, "Any category", "Any description");
    }

    @Test
    void shouldSaveCategorySuccessfully() {
        when(this.categoryGateway.saveCategory(this.category)).thenReturn(this.category);

        Category result = this.saveCategory.execute(this.category);

        assertEquals(this.category, result);
        verify(this.categoryGateway, times(1)).saveCategory(this.category);
    }

    @Test
    void shouldPropagateExceptionWhenGatewayFails() {
        doThrow(mock(DataAccessException.class)).when(this.categoryGateway).saveCategory(this.category);

        assertThrows(DataAccessException.class, () -> this.saveCategory.execute(this.category));
        verify(this.categoryGateway, times(1)).saveCategory(this.category);
    }
}
