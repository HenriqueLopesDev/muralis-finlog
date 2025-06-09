package org.finlog.finlogbackendspring.business.expense.infrastructure.http.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.finlog.finlogbackendspring.business.expense.application.service.ExpenseService;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseCreateRequest;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.response.ExpenseListDto;
import org.finlog.finlogbackendspring.config.http.response.ErrorResponse;
import org.finlog.finlogbackendspring.config.http.response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<SuccessResponse<List<ExpenseListDto>>> getAllExpenses() {
        return ResponseEntity.ok().body(
                new SuccessResponse<>(this.expenseService.getAllExpenses())
        );
    }

    @PostMapping()
    @Operation(summary = "Create a new expense", description = "Creates a new expense with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expense created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<SuccessResponse<Long>> createExpense(@Valid @RequestBody ExpenseCreateRequest expenseCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new SuccessResponse<>(
                        this.expenseService.createExpense(expenseCreateRequest)
                )
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an expense", description = "Deletes a specific expense by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Expense deleted successfully",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "404", description = "Expense not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> deleteExpense(@Parameter(name = "id", description = "ID of the expense to delete", example = "123", required = true)
                                                               @PathVariable Long id) {
        this.expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
