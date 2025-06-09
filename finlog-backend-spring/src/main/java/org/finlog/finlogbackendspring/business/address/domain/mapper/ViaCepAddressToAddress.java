package org.finlog.finlogbackendspring.business.address.domain.mapper;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.infrastructure.http.dto.response.ViaCepResponse;
import org.finlog.finlogbackendspring.config.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ViaCepAddressToAddress implements Mapper<ViaCepResponse, Address> {

    public Address map(ViaCepResponse viaCepResponse) {
        Address address = new Address();
        address.setZipCode(viaCepResponse.cep());
        address.setStreet(viaCepResponse.logradouro());
        address.setComplement(viaCepResponse.complemento());
        address.setNeighborhood(viaCepResponse.bairro());
        address.setCity(viaCepResponse.localidade());
        address.setState(viaCepResponse.uf());

        return address;
    }
}
