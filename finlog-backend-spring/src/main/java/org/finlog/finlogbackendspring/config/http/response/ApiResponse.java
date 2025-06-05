package org.finlog.finlogbackendspring.config.http.response;

public abstract class ApiResponse {
    private final boolean success;

    protected ApiResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}