package org.finlog.finlogbackendspring.business.address.domain.useCase;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.domain.gateway.AddressApiGateway;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.springframework.stereotype.Component;

@Component
public class FindAddressByZipCode implements UseCase<String, Address> {

    private final AddressApiGateway addressApiGateway;

    public FindAddressByZipCode(AddressApiGateway addressApiGateway) {
        this.addressApiGateway = addressApiGateway;
    }

    public Address execute(String zipCode) {
        return this.addressApiGateway.findAddressByZipCode(zipCode);
    }
}
