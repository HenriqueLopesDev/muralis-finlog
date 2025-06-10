package org.finlog.finlogbackendspring.business.address.useCase;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.finlog.finlogbackendspring.business.address.domain.gateway.AddressGateway;
import org.finlog.finlogbackendspring.business.address.domain.useCase.SaveAddress;
import org.finlog.finlogbackendspring.config.domain.useCase.UseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SaveAddressTest {

    private AddressGateway addressGateway;
    private UseCase<Address, Address> saveAddress;
    private Address mockAddress;

    @BeforeEach
    void setUp() {
        this.addressGateway = mock(AddressGateway.class);
        this.saveAddress = new SaveAddress(this.addressGateway);
        this.mockAddress = new Address();
    }

    @Test
    void shouldSaveAddressSuccessfully() {
        when(addressGateway.saveAddress(this.mockAddress)).thenReturn(this.mockAddress);

        Address result = this.saveAddress.execute(this.mockAddress);

        assertEquals(this.mockAddress, result);
        verify(addressGateway, times(1)).saveAddress(this.mockAddress);
    }

    @Test
    void shouldThrowExceptionWhenGatewayFails() {
        when(addressGateway.saveAddress(this.mockAddress)).thenThrow(mock(DataAccessException.class));

        assertThrows(DataAccessException.class, () -> this.saveAddress.execute(this.mockAddress));
        verify(addressGateway, times(1)).saveAddress(this.mockAddress);
    }
}
