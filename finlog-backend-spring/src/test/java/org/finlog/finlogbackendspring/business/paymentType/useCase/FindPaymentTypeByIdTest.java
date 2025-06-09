package org.finlog.finlogbackendspring.business.paymentType.useCase;

import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.business.paymentType.domain.exception.PaymentTypeNotFoundException;
import org.finlog.finlogbackendspring.business.paymentType.domain.gateway.PaymentTypeGateway;
import org.finlog.finlogbackendspring.business.paymentType.domain.useCase.FindPaymentTypeById;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FindPaymentTypeByIdTest {


    private PaymentTypeGateway paymentTypeGateway;
    private FindPaymentTypeById findPaymentTypeById;
    private final Long paymentTypeId = 1L;
    private PaymentType paymentType;

    @BeforeEach
    void setUp() {
        this.paymentTypeGateway = mock(PaymentTypeGateway.class);
        this.findPaymentTypeById = new FindPaymentTypeById(this.paymentTypeGateway);
        this.paymentType = new PaymentType(this.paymentTypeId, "Pix");
    }

    @Test
    void shouldReturnPaymentTypeWhenFound() {
        when(this.paymentTypeGateway.findPaymentTypeById(this.paymentTypeId)).thenReturn(this.paymentType);

        PaymentType result = this.findPaymentTypeById.execute(this.paymentTypeId);

        assertNotNull(result);
        assertEquals(this.paymentType, result);
        verify(this.paymentTypeGateway, times(1)).findPaymentTypeById(this.paymentTypeId);
    }

    @Test
    void shouldThrowPaymentTypeNotFoundExceptionWhenGatewayThrows() {
        when(this.paymentTypeGateway.findPaymentTypeById(this.paymentTypeId)).thenThrow(mock(DataAccessException.class));

        PaymentTypeNotFoundException exception = assertThrows(PaymentTypeNotFoundException.class, () -> {
            this.findPaymentTypeById.execute(this.paymentTypeId);
        });

        assertEquals("Error finding payment type by ID " + this.paymentType.getId(), exception.getMessage());
        verify(this.paymentTypeGateway, times(1)).findPaymentTypeById(this.paymentTypeId);
    }
}
