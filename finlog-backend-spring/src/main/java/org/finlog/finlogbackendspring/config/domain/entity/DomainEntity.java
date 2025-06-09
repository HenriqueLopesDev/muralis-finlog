package org.finlog.finlogbackendspring.config.domain.entity;

public abstract class DomainEntity {

    private Long id;

    public DomainEntity() {
    }

    public DomainEntity(Long id) {
        this.setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
