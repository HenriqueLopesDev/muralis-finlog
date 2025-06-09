package org.finlog.finlogbackendspring.business.address.domain.exception;

public class CepNotFoundException extends RuntimeException{
    public CepNotFoundException(String message) {
        super("CEP not found: " + message);
    }
}
