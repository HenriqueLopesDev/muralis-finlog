package org.finlog.finlogbackendspring.business.address.infrastructure.http.dto;

public record AddressByCep(
        String zipCode,
        String state,
        String city,
        String neighborhood,
        String street,
        String complement
) {}