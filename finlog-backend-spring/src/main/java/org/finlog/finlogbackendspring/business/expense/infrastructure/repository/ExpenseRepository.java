package org.finlog.finlogbackendspring.business.expense.infrastructure.repository;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseRepository implements ExpenseGateway {

    private final JdbcTemplate jdbcTemplate;

    public ExpenseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Expense> findAllExpenses() {
        String sql = "SELECT *, categories, payment_types, addresses " +
                "FROM expenses " +
                "JOIN categories " +
                "ON expenses.exp_cat_id = categories.cat_id " +
                "JOIN payment_types " +
                "ON expenses.exp_pmt_id = payment_types.pmt_id " +
                "JOIN addresses ON expenses.exp_adr_id = addresses.adr_id " +
                "WHERE exp_active = true";
        return jdbcTemplate.query(sql, this.expenseRowMapper);
    }

    public Expense findExpenseById(Long id) throws DataAccessException {
        String sql = "SELECT *, categories, payment_types, addresses " +
                "FROM expenses " +
                "JOIN categories ON expenses.exp_cat_id = categories.cat_id " +
                "JOIN payment_types ON expenses.exp_pmt_id = payment_types.pmt_id " +
                "JOIN addresses ON expenses.exp_adr_id = addresses.adr_id " +
                "WHERE exp_id = ? AND exp_active = true";

        return jdbcTemplate.queryForObject(sql, this.expenseRowMapper, id);
    }

    public void updateExpense(Expense expense) {
        String sql = "UPDATE expenses SET exp_value = ?, exp_description = ?, exp_date = ?, exp_cat_id = ?, exp_pmt_id = ?, exp_adr_id = ?, exp_active = ? " +
                "WHERE exp_id = ?";

        jdbcTemplate.update(sql,
                expense.getValue(),
                expense.getDescription(),
                expense.getDate(),
                expense.getCategory().getId(),
                expense.getPaymentType().getId(),
                expense.getAddress().getId(),
                expense.getActive(),
                expense.getId());
    }

    public Long saveExpense(Expense expense) {
        String sql = "INSERT INTO expenses (exp_value, exp_description, exp_date, exp_cat_id, exp_pmt_id, exp_adr_id, exp_active) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING exp_id";

        return jdbcTemplate.queryForObject(sql, Long.class,
                expense.getValue(),
                expense.getDescription(),
                expense.getDate(),
                expense.getCategory().getId(),
                expense.getPaymentType().getId(),
                expense.getAddress().getId(),
                expense.getActive());
    }

    private final RowMapper<Expense> expenseRowMapper = (rs, rowNum) -> {
        Expense expense = new Expense();
        expense.setId(rs.getLong("exp_id"));
        expense.setValue(rs.getBigDecimal("exp_value"));
        expense.setDescription(rs.getString("exp_description"));

        expense.setDate(rs.getTimestamp("exp_date").toLocalDateTime());

        Category category = new Category(
                rs.getLong("cat_id"),
                rs.getString("cat_name"),
                rs.getString("cat_description")
        );
        expense.setCategory(category);

        PaymentType paymentType = new PaymentType(
                rs.getLong("pmt_id"),
                rs.getString("pmt_type")
        );
        expense.setPaymentType(paymentType);

        Address address = new Address(
                rs.getLong("adr_id"),
                rs.getString("adr_zip_code"),
                rs.getString("adr_state"),
                rs.getString("adr_city"),
                rs.getString("adr_neighborhood"),
                rs.getString("adr_street"),
                rs.getString("adr_number"),
                rs.getString("adr_complement")
        );
        expense.setAddress(address);

        return expense;
    };
}
