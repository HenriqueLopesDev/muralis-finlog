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
import org.finlog.finlogbackendspring.business.expense.domain.entity.Expense;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseFilters;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.request.ExpenseCreateRequest;
import org.finlog.finlogbackendspring.business.expense.infrastructure.http.dto.response.ExpenseListDto;
import org.finlog.finlogbackendspring.config.http.response.ErrorResponse;
import org.finlog.finlogbackendspring.config.pagination.PaginatedData;
import org.finlog.finlogbackendspring.config.http.response.PaginatedResponse;
import org.finlog.finlogbackendspring.config.http.response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
    public ResponseEntity<PaginatedResponse<PaginatedData<ExpenseListDto>>> getAllExpenses(@RequestParam(defaultValue = "1") int page, @RequestParam(required = false) String startDate,
                                                                                           @RequestParam(required = false) String endDate) {
        return ResponseEntity.ok().body(
                new PaginatedResponse<>(this.expenseService.getAllExpenses(new ExpenseFilters(page, startDate, endDate)))
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get expense by ID", description = "Retrieves a specific expense by its ID.")
    public ResponseEntity<SuccessResponse<Expense>> getExpenseById(@PathVariable long id) {
        return ResponseEntity.ok().body(new SuccessResponse<>(this.expenseService.getExpenseById(id)));
    }

    @GetMapping("/total")
    @Operation(summary = "Get total expenses", description = "Retrieves the total amount of all expenses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Total expenses retrieved successfully",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "404", description = "No expenses found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<SuccessResponse<BigDecimal>> getTotalExpenses(@RequestParam String startDate,
                                                                    @RequestParam String endDate) {
        return ResponseEntity.ok().body(
                new SuccessResponse<>(this.expenseService.getTotalExpenses(startDate, endDate))
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

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing expense", description = "Updates the details of an existing expense.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense updated successfully",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Expense not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<SuccessResponse<Void>> updateExpense(@PathVariable long id, @Valid @RequestBody ExpenseCreateRequest expenseCreateRequest) {
        this.expenseService.updateExpense(id, expenseCreateRequest);
        return ResponseEntity.ok().body(
                new SuccessResponse<>(null)
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
