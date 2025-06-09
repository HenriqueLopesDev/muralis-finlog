package org.finlog.finlogbackendspring.business.address.infrastructure.http.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.finlog.finlogbackendspring.business.address.application.AddressService;
import org.finlog.finlogbackendspring.business.address.infrastructure.http.dto.AddressByCep;
import org.finlog.finlogbackendspring.config.http.response.ErrorResponse;
import org.finlog.finlogbackendspring.config.http.response.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
@Tag(name = "Address", description = "Endpoints for managing addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{zipCode}")
    @Operation(
            summary = "Find address by zip code",
            description = "Returns address data for a valid Brazilian zip code (CEP)."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Address found successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid or non-existent zip code",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public ResponseEntity<SuccessResponse<AddressByCep>> getAddressByZipCode(@PathVariable String zipCode) {
        return ResponseEntity.ok().body(
                new SuccessResponse<>(this.addressService.getAddressByZipCode(zipCode))
        );
    }
}
