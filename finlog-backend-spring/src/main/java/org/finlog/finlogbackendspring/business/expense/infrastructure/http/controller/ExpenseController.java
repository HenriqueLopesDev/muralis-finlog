package org.finlog.finlogbackendspring.business.expense.infrastructure.http.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.finlog.finlogbackendspring.business.expense.application.service.ExpenseService;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.ExpenseListDto;
import org.finlog.finlogbackendspring.config.http.response.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/despesas")
@Tag(name = "Expense", description = "Endpoints for managing expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping()
    @Operation(summary = "Get all expenses", description = "Retrieves a list of all recorded expenses.")
    public ResponseEntity<SuccessResponse<List<ExpenseListDto>>> getAllExpenses(){
        return ResponseEntity.ok().body(
                new SuccessResponse<>(this.expenseService.getAllExpenses())
        );
    }
}
