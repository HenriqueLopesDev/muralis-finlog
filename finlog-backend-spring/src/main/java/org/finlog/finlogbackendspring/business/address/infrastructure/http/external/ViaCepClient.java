package org.finlog.finlogbackendspring.business.address.infrastructure.http.external;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.domain.exception.CepNotFoundException;
import org.finlog.finlogbackendspring.business.address.domain.gateway.AddressApiGateway;
import org.finlog.finlogbackendspring.business.address.infrastructure.http.dto.ViaCepResponse;
import org.finlog.finlogbackendspring.config.mapper.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;

@Component
public class ViaCepClient implements AddressApiGateway {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Mapper<ViaCepResponse, Address> viaCepResponseAddressMapper;

    @Value("${viacep.api.url}")
    private String viaCepUrl;

    public ViaCepClient(Mapper<ViaCepResponse, Address> viaCepResponseAddressMapper) {
        this.viaCepResponseAddressMapper = viaCepResponseAddressMapper;
    }

    public Address findAddressByZipCode (String zipCode) throws RestClientException {
        ViaCepResponse response = this.restTemplate.getForObject(this.viaCepUrl + zipCode + "/json/", ViaCepResponse.class);

        if (response == null || "true".equals(response.erro())){
            throw new CepNotFoundException(zipCode);
        }

        return this.viaCepResponseAddressMapper.map(response);
    }
}
