package org.finlog.finlogbackendspring.business.address.infrastructure.repository;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.domain.gateway.AddressGateway;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository implements AddressGateway {

    private final JdbcTemplate jdbcTemplate;

    public AddressRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Address saveAddress(Address address) {
        String sql = "INSERT INTO addresses (adr_zip_code, adr_state, adr_city, adr_neighborhood, adr_street, adr_number, adr_complement) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING *;";
        return this.jdbcTemplate.queryForObject(sql, this.addressRowMapper,
                address.getZipCode(),
                address.getState(),
                address.getCity(),
                address.getNeighborhood(),
                address.getStreet(),
                address.getStreetNumber(),
                address.getComplement().orElse(null)
        );
    }

    private final RowMapper<Address> addressRowMapper = (rs, rowNum) -> {
        Address addressRow = new Address();
        addressRow.setId(rs.getLong("adr_id"));
        addressRow.setZipCode(rs.getString("adr_zip_code"));
        addressRow.setState(rs.getString("adr_state"));
        addressRow.setCity(rs.getString("adr_city"));
        addressRow.setNeighborhood(rs.getString("adr_neighborhood"));
        addressRow.setStreet(rs.getString("adr_street"));
        addressRow.setStreetNumber(rs.getString("adr_number"));
        addressRow.setComplement(rs.getString("adr_complement"));
        return addressRow;
    };
}
