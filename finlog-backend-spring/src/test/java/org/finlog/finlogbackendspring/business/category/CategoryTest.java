package org.finlog.finlogbackendspring.business.category;

import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {

    @Test
    void shouldCreateCategoryAndGetValues() {
        Category category = new Category(1L, "Food", "Expenses related to food");

        assertEquals(1L, category.getId());
        assertEquals("Food", category.getName());
        assertEquals("Expenses related to food", category.getDescription());
    }

    @Test
    void shouldSetCategoryValues() {
        Category category = new Category();
        category.setId(2L);
        category.setName("Transport");
        category.setDescription("Expenses related to transportation");

        assertEquals(2L, category.getId());
        assertEquals("Transport", category.getName());
        assertEquals("Expenses related to transportation", category.getDescription());
    }
}
