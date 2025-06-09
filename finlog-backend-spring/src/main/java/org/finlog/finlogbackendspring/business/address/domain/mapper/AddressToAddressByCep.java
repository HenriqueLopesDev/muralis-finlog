package org.finlog.finlogbackendspring.business.address.domain.mapper;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.infrastructure.http.dto.AddressByCep;
import org.finlog.finlogbackendspring.config.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressByCep implements Mapper<Address, AddressByCep>{

    public AddressByCep map(Address address) {
        return new AddressByCep(
            address.getZipCode(),
            address.getState(),
            address.getCity(),
            address.getNeighborhood(),
            address.getStreet(),
            address.getComplement().orElse(null)
        );
    }

}
