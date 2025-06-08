package org.finlog.finlogbackendspring.config.mapper;

public abstract class AbstractMapper<D, DTO> {

    public DTO toDto(D domain) {
        throw new UnsupportedOperationException("Mapping to DTO not supported.");
    }

    public D toDomain(DTO dto) {
        throw new UnsupportedOperationException("Mapping to domain not supported.");
    }
}
