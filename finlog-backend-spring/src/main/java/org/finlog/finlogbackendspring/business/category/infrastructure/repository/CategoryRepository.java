package org.finlog.finlogbackendspring.business.category.infrastructure.repository;

import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.category.domain.gateway.CategoryGateway;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository implements CategoryGateway {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Category saveCategory(Category category) {
        String sql = "INSERT INTO categories (cat_name, cat_description) VALUES (?, ?) RETURNING *";
        return jdbcTemplate.queryForObject(sql, categoryRowMapper, category.getName(), category.getDescription());
    }

    public Void updateCategory(Category category) {
        String sql = "UPDATE categories SET cat_name = ?, cat_description = ? WHERE cat_id = ?";
        jdbcTemplate.update(sql, category.getName(), category.getDescription(), category.getId());
        return null;
    }

    private final RowMapper<Category> categoryRowMapper = (rs, rowNum) -> {
        Category categoryRow = new Category();
        categoryRow.setId(rs.getLong("cat_id"));
        categoryRow.setName(rs.getString("cat_name"));
        categoryRow.setDescription(rs.getString("cat_description"));
        return categoryRow;
    };
}
