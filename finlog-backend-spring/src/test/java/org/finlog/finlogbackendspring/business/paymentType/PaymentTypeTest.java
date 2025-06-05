package org.finlog.finlogbackendspring.business.paymentType;

import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTypeTest {


    @Test
    void shouldCreatePaymentTypeAndGetValues() {
        PaymentType paymentType = new PaymentType(1L, "Credit");

        assertEquals(1L, paymentType.getId());
        assertEquals("Credit", paymentType.getType());
    }

    @Test
    void shouldSetPaymentTypeValues() {
        PaymentType paymentType = new PaymentType();
        paymentType.setId(2L);
        paymentType.setType("Debit");

        assertEquals(2L, paymentType.getId());
        assertEquals("Debit", paymentType.getType());
    }
}
