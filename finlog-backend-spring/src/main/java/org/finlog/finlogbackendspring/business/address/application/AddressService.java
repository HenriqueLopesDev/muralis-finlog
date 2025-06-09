package org.finlog.finlogbackendspring.business.address.application;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.domain.mapper.AddressToAddressByCep;
import org.finlog.finlogbackendspring.business.address.domain.useCase.FindAddressByZipCode;
import org.finlog.finlogbackendspring.business.address.infrastructure.http.dto.AddressByCep;
import org.finlog.finlogbackendspring.config.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final FindAddressByZipCode findAddressByZipCode;
    private final Mapper<Address, AddressByCep> addressByCepMapper;

    public AddressService(FindAddressByZipCode findAddressByZipCode, AddressToAddressByCep addressToAddressByCep) {
        this.findAddressByZipCode = findAddressByZipCode;
        this.addressByCepMapper = addressToAddressByCep;
    }

    public AddressByCep getAddressByZipCode(String zipCode) {
        return this.addressByCepMapper.map(
                this.findAddressByZipCode.execute(zipCode)
        );
    }
}
