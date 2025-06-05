package org.finlog.finlogbackendspring.business.paymentType.infrastructure.repository;

import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.business.paymentType.domain.gateway.PaymentTypeGateway;
import org.springframework.jdbc.core.JdbcTemplate;
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
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new PaymentType(rs.getLong("pmt_id"), rs.getString("pmt_type"))
        );
    }
}
