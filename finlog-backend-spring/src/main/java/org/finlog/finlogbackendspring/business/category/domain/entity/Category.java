package org.finlog.finlogbackendspring.business.category.domain.entity;

import org.finlog.finlogbackendspring.config.domain.entity.DomainEntity;

public class Category extends DomainEntity {

    private String name;
    private String description;

    public Category() {
    }

    public Category(long id, String name, String description) {
        super(id);
        this.setName(name);
        this.setDescription(description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
