package org.finlog.finlogbackendspring.business.expense.infrastructure.repository;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.category.domain.entity.Category;
import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.domain.gateway.ExpenseGateway;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseFilters;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseTotalValueRequest;
import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.config.pagination.PaginatedResult;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpenseRepository implements ExpenseGateway {

    private final JdbcTemplate jdbcTemplate;
    private final int PAGE_SIZE = 10;

    public ExpenseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PaginatedResult<Expense> findAllExpenses(ExpenseFilters expenseFilters) {
        int totalItems = this.countTotalExpenses(expenseFilters.startDate(), expenseFilters.endDate());

        if (totalItems == 0) {
            return new PaginatedResult<>(List.of(), expenseFilters.page(), 0, 0);
        }

        int totalPages = (int) calculateTotalPages(totalItems);
        List<Expense> content = fetchExpensesPage(expenseFilters.page(), expenseFilters.startDate(), expenseFilters.endDate());

        return new PaginatedResult<>(content, expenseFilters.page(), totalItems, totalPages);
    }

    private int countTotalExpenses(String startDate, String endDate) {
        StringBuilder countSql = new StringBuilder("SELECT COUNT(*) FROM expenses WHERE exp_active = true");

        List<Object> params = new ArrayList<>();
        this.appendDateFilter(
                countSql,
                startDate,
                endDate,
                params
        );

        Integer result = jdbcTemplate.queryForObject(
                countSql.toString(),
                Integer.class,
                params.toArray()
        );
        return result != null ? result : 0;
    }

    private double calculateTotalPages(double totalItems) {
        return Math.ceil(totalItems / PAGE_SIZE);
    }

    private List<Expense> fetchExpensesPage(int page, String startDate, String endDate) {
        int offset = (page - 1) * PAGE_SIZE;

        StringBuilder sql = new StringBuilder("""
                SELECT *
                FROM expenses
                JOIN categories ON expenses.exp_cat_id = categories.cat_id
                JOIN payment_types ON expenses.exp_pmt_id = payment_types.pmt_id
                JOIN addresses ON expenses.exp_adr_id = addresses.adr_id
                WHERE exp_active = true
                """);

        List<Object> params = new ArrayList<>();
        this.appendDateFilter(
                sql,
                startDate,
                endDate,
                params
        );

        sql.append(" ORDER BY exp_id LIMIT ? OFFSET ?");
        params.add(PAGE_SIZE);
        params.add(offset);

        return jdbcTemplate.query(
                sql.toString(),
                this.expenseRowMapper,
                params.toArray()
        );
    }

    private void appendDateFilter(StringBuilder sql, String startDate, String endDate, List<Object> params) {

        if (startDate != null && endDate != null) {
            sql.append(" AND exp_date BETWEEN ? AND ?");
            params.add(Date.valueOf(LocalDate.parse(startDate)));
            params.add(Date.valueOf(LocalDate.parse(endDate)));
        }
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

    @Override
    public BigDecimal getTotalExpense(ExpenseTotalValueRequest expenseTotalValueRequest) {
        StringBuilder sql = new StringBuilder("SELECT SUM(exp_value) FROM expenses WHERE exp_active = true");

        List<Object> params = new ArrayList<>();
        this.appendDateFilter(
                sql,
                expenseTotalValueRequest.startDate(),
                expenseTotalValueRequest.endDate(),
                params
        );

        BigDecimal totalExpense = jdbcTemplate.queryForObject(
                sql.toString(),
                BigDecimal.class,
                params.toArray()
        );

        return totalExpense != null ? totalExpense : BigDecimal.ZERO;
    }

    private final RowMapper<Expense> expenseRowMapper = (rs, rowNum) -> {
        Expense expense = new Expense();
        expense.setId(rs.getLong("exp_id"));
        expense.setValue(rs.getBigDecimal("exp_value"));
        expense.setDescription(rs.getString("exp_description"));
        expense.setActive(rs.getBoolean("exp_active"));

        expense.setDate(rs.getDate("exp_date").toLocalDate());

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
