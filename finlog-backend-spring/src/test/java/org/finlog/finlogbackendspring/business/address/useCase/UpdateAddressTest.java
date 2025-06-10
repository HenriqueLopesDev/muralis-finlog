package org.finlog.finlogbackendspring.business.address.useCase;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.domain.gateway.AddressGateway;
import org.finlog.finlogbackendspring.business.address.domain.useCase.UpdateAddress;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UpdateAddressTest {

    private AddressGateway addressGateway;
    private UseCase<Address, Void> updateAddress;
    private Address mockAddress;

    @BeforeEach
    void setUp() {
        this.addressGateway = mock(AddressGateway.class);
        this.updateAddress = new UpdateAddress(this.addressGateway);
        this.mockAddress = new Address();
    }

    @Test
    void shouldUpdateAddressSuccessfully() {

        doNothing().when(addressGateway).updateAddress(mockAddress);

        this.updateAddress.execute(mockAddress);

        verify(addressGateway, times(1)).updateAddress(mockAddress);
    }

    @Test
    void shouldThrowExceptionWhenGatewayFails() {

        doThrow(mock(DataAccessException.class))
                .when(addressGateway).updateAddress(this.mockAddress);

        assertThrows(DataAccessException.class, () -> this.updateAddress.execute(this.mockAddress));

        verify(addressGateway, times(1)).updateAddress(this.mockAddress);
    }
}
