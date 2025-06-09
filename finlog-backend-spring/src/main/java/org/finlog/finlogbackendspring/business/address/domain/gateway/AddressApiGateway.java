package org.finlog.finlogbackendspring.business.address.domain.gateway;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;

public interface AddressApiGateway {

    Address findAddressByZipCode(String zipCode);
}
