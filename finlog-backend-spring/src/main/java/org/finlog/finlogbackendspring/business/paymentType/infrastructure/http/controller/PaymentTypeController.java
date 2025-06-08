package org.finlog.finlogbackendspring.business.paymentType.infrastructure.http.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Payment Type", description = "Endpoints for managing payment types")
public class PaymentTypeController {

    private final QuotationService quotationService;

    public PaymentTypeController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }


    @GetMapping()
    @Operation(summary = "Get all payment types", description = "Retrieves a list of all available payment types.")
    public ResponseEntity<SuccessResponse<List<PaymentType>>> getAllPaymentTypes() {
        return ResponseEntity.ok().body(
                new SuccessResponse<>(this.quotationService.getAllPaymentTypes())
        );
    }
}
