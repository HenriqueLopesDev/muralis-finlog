package org.finlog.finlogbackendspring.business.address.domain.useCase;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.domain.gateway.AddressGateway;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.springframework.stereotype.Component;

@Component
public class SaveAddress implements UseCase<Address, Address> {

    private final AddressGateway addressGateway;

    public SaveAddress(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    public Address execute(Address address) {
        return this.addressGateway.saveAddress(address);
    }
}
