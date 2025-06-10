package org.finlog.finlogbackendspring.config.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PaginatedData<T> (
        List<T> content,

        @JsonProperty("pagination")
        PaginationMeta meta
){
    public PaginatedData(List<T> content, int currentPage, int totalPages, int totalItems) {
        this(content, new PaginationMeta(currentPage, totalPages, totalItems));
    }
}
