package org.finlog.finlogbackendspring.config.http.response;

import org.finlog.finlogbackendspring.config.pagination.PaginatedData;

public class PaginatedResponse<T extends PaginatedData<?>> extends SuccessResponse<T> {

    public PaginatedResponse(T data) {
        super(data);
    }
}
