package org.finlog.finlogbackendspring.business.paymentType.infrastructure.repository;

import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.business.paymentType.domain.gateway.PaymentTypeGateway;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentTypeRepository implements PaymentTypeGateway {

    private final JdbcTemplate jdbcTemplate;

    public PaymentTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PaymentType> findAllPaymentTypes() {
        String sql = "SELECT * FROM payment_types";
        return jdbcTemplate.query(sql, this.paymentTypeRowMapper);
    }

    public PaymentType findPaymentTypeById(Long id) {
        String sql = "SELECT * FROM payment_types WHERE pmt_id = ?";
        return jdbcTemplate.queryForObject(sql, this.paymentTypeRowMapper, id);
    }

    private final RowMapper<PaymentType> paymentTypeRowMapper = (rs, rowNum) -> new PaymentType(
                rs.getLong("pmt_id"),
                rs.getString("pmt_type")
    );
}
