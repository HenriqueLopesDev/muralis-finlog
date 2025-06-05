package org.finlog.finlogbackendspring.config.http.response;

public class SuccessResponse<T> extends ApiResponse {
    private final T data;

    public SuccessResponse(T data) {
        super(true);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
