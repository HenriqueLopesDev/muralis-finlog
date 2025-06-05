package org.finlog.finlogbackendspring.business.paymentType.useCase;

import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.business.paymentType.domain.gateway.PaymentTypeGateway;
import org.finlog.finlogbackendspring.business.paymentType.domain.useCase.FindAllPaymentTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FindAllPaymentTypesTest {

    private PaymentTypeGateway paymentTypeGateway;
    private FindAllPaymentTypes findAllPaymentTypes;

    @BeforeEach
    void setUp() {
        this.paymentTypeGateway = mock(PaymentTypeGateway.class);
        this.findAllPaymentTypes = new FindAllPaymentTypes(this.paymentTypeGateway);
    }

    @Test
    void shouldReturnListOfPaymentTypes() {
        List<PaymentType> mockList = Arrays.asList(
                new PaymentType(1L, "Credit"),
                new PaymentType(2L, "Debit")
        );

        when(this.paymentTypeGateway.findAllPaymentTypes()).thenReturn(mockList);

        List<PaymentType> result = this.findAllPaymentTypes.execute();

        assertEquals(2, result.size());
        verify(this.paymentTypeGateway, times(1)).findAllPaymentTypes();
    }

    @Test
    void shouldReturnEmptyListWhenNoPaymentTypesFound() {
        when(this.paymentTypeGateway.findAllPaymentTypes()).thenReturn(List.of());

        List<PaymentType> result = this.findAllPaymentTypes.execute();

        assertTrue(result.isEmpty());
        verify(this.paymentTypeGateway, times(1)).findAllPaymentTypes();
    }
}
