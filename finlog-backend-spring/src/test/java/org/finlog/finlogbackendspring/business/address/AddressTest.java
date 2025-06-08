package org.finlog.finlogbackendspring.business.address;

import org.finlog.finlogbackendspring.business.address.domain.entity.Address;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {

    @Test
    void shouldCreateAddressAndGetValues() {
        Address address = new Address(
                1L,
                "00000000",
                "SP",
                "São Paulo",
                "Neighborhood one",
                "Street one",
                "123",
                "Complement one"
        );

        assertEquals(1L, address.getId());
        assertEquals("00000000", address.getZipCode());
        assertEquals("SP", address.getState());
        assertEquals("São Paulo", address.getCity());
        assertEquals("Neighborhood one", address.getNeighborhood());
        assertEquals("Street one", address.getStreet());
        assertEquals("123", address.getStreetNumber());
        assertEquals(Optional.of("Complement one"), address.getComplement());
    }

    @Test
    void shouldSetAddressValues() {
        Address address = new Address();
        address.setId(2L);
        address.setZipCode("00000000");
        address.setState("SP");
        address.setCity("São Paulo");
        address.setNeighborhood("Neighborhood one");
        address.setStreet("Street one");
        address.setStreetNumber("123");
        address.setComplement(null);

        assertEquals(2L, address.getId());
        assertEquals("00000000", address.getZipCode());
        assertEquals("SP", address.getState());
        assertEquals("São Paulo", address.getCity());
        assertEquals("Neighborhood one", address.getNeighborhood());
        assertEquals("Street one", address.getStreet());
        assertEquals("123", address.getStreetNumber());
        assertEquals(Optional.empty(), address.getComplement());
    }
}
