package org.finlog.finlogbackendspring.business.paymentType.infrastructure.http.controller;

import org.finlog.finlogbackendspring.business.paymentType.application.service.QuotationService;
import org.finlog.finlogbackendspring.business.paymentType.domain.entity.PaymentType;
import org.finlog.finlogbackendspring.config.http.response.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipos-pagamento")
public class PaymentTypeController {

    private final QuotationService quotationService;

    public PaymentTypeController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }


    @GetMapping()
    public ResponseEntity<SuccessResponse<List<PaymentType>>> getAllPaymentTypes() {
        return ResponseEntity.ok().body(
                new SuccessResponse<>(this.quotationService.getAllPaymentTypes())
        );
    }
}
