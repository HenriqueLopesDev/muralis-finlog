package org.finlog.finlogbackendspring.business.address.infrastructure.http.dto.response;

public record AddressByCep(
        String zipCode,
        String state,
        String city,
        String neighborhood,
        String street,
        String complement
) {}