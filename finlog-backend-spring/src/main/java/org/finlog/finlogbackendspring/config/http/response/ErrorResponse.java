package org.finlog.finlogbackendspring.config.http.response;

public class ErrorResponse extends ApiResponse {
    private final String error;

    public ErrorResponse(String error) {
        super(false);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
