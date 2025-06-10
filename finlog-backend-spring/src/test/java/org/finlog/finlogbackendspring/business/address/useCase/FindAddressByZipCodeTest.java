package org.finlog.finlogbackendspring.business.address.useCase;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.domain.exception.CepNotFoundException;
import org.finlog.finlogbackendspring.business.address.domain.gateway.AddressApiGateway;
import org.finlog.finlogbackendspring.business.address.domain.useCase.FindAddressByZipCode;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FindAddressByZipCodeTest {

    private AddressApiGateway addressApiGateway;
    private UseCase<String, Address> findAddressByZipCode;

    private final String VALID_ZIP = "01310-100";
    private final String INVALID_ZIP = "00000-000";

    @BeforeEach
    void setUp() {
        this.addressApiGateway = mock(AddressApiGateway.class);
        this.findAddressByZipCode = new FindAddressByZipCode(this.addressApiGateway);
    }

    @Test
    void shouldReturnAddressForValidZipCode() {
        Address mockAddress = new Address();

        when(addressApiGateway.findAddressByZipCode(VALID_ZIP)).thenReturn(mockAddress);

        Address result = this.findAddressByZipCode.execute(VALID_ZIP);

        assertNotNull(result);
        verify(addressApiGateway, times(1)).findAddressByZipCode(VALID_ZIP);
    }

    @Test
    void shouldThrowExceptionForInvalidZipCode() {
        when(addressApiGateway.findAddressByZipCode(INVALID_ZIP))
                .thenThrow(new CepNotFoundException(INVALID_ZIP));

        assertThrows(CepNotFoundException.class, () -> this.findAddressByZipCode.execute(INVALID_ZIP));

        verify(addressApiGateway, times(1)).findAddressByZipCode(INVALID_ZIP);
    }
}
