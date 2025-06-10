package org.finlog.finlogbackendspring.business.address.domain.useCase;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.domain.gateway.AddressGateway;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.springframework.stereotype.Component;

@Component
public class UpdateAddress implements UseCase<Address, Void> {

    private final AddressGateway addressGateway;

    public UpdateAddress(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    public Void execute(Address address) {
        return this.addressGateway.updateAddress(address);
    }
}
