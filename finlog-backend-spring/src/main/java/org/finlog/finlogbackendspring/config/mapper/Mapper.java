package org.finlog.finlogbackendspring.config.mapper;

public interface Mapper<baseEntity, mappedEntity> {

    mappedEntity map(baseEntity baseEntity);
}
