package org.finlog.finlogbackendspring.config.pagination;

public record PaginationMeta (
        int currentPage,
        int totalPages,
        int totalItems
){}
