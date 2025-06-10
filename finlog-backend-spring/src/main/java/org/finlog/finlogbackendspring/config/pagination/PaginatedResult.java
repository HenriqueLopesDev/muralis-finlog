package org.finlog.finlogbackendspring.config.pagination;

import java.util.List;

public record PaginatedResult<T> (
        List<T> content,
        int currentPage,
        int totalItems,
        int totalPages
){}
