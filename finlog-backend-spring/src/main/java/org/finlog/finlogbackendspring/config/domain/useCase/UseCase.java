package org.finlog.finlogbackendspring.config.domain.useCase;

public interface UseCase<input, output> {

    output execute(input input);
}
