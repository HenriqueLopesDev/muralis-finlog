package org.finlog.finlogbackendspring.config.domain.mapper;

public interface Mapper<baseEntity, mappedEntity> {

    mappedEntity map(baseEntity baseEntity);
}
