package org.example.ecommerce.api.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.ecommerce.api.swagger.annotations.responses.BadRequestResponse;
import org.example.ecommerce.api.swagger.annotations.responses.NotFoundResponse;
import org.example.ecommerce.application.useCases.item.deleteById.DeleteItemByIdUseCase;
import org.example.ecommerce.application.useCases.item.getById.GetItemByIdUseCase;
import org.example.ecommerce.application.useCases.item.register.RegisterItemUseCase;
import org.example.ecommerce.shared.communication.request.item.RequestItemJson;
import org.example.ecommerce.shared.communication.response.item.ResponseRegisteredItemJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/item")
@Tag(name= "item")
public class ItemController {

    private final RegisterItemUseCase registerItemUseCase;
    private final GetItemByIdUseCase getItemByIdUseCase;
    private final DeleteItemByIdUseCase deleteItemByIdUseCase;

    public  ItemController(
            RegisterItemUseCase registerItemUseCase,
            GetItemByIdUseCase getItemByIdUseCase,
            DeleteItemByIdUseCase deleteItemByIdUseCase
    ) {
        this.registerItemUseCase = registerItemUseCase;
        this.getItemByIdUseCase = getItemByIdUseCase;
        this.deleteItemByIdUseCase =  deleteItemByIdUseCase;
    }


    @PostMapping
    @Operation(
            security = {
                    @SecurityRequirement(name = "bearer-key")
            }
    )
    @ApiResponse(
            responseCode = "201",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseRegisteredItemJson.class)
            )
    )
    @BadRequestResponse
    public ResponseEntity<ResponseRegisteredItemJson> register(
            @Valid  @RequestPart("data") RequestItemJson request,
            @RequestPart("image") MultipartFile image
    ) throws IOException {
        var response = registerItemUseCase.execute(request, image.getBytes());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(
            security = {
                    @SecurityRequirement(name = "bearer-key")
            }
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseRegisteredItemJson.class)
            )
    )
    @NotFoundResponse
    public ResponseEntity<ResponseRegisteredItemJson> getItem(
            @PathVariable UUID id
    ){
        var response = getItemByIdUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            security = {
                    @SecurityRequirement(name = "bearer-key")
            }
    )
    @ApiResponse(
            responseCode = "204"
    )
    @NotFoundResponse
    public ResponseEntity<?> deleteItem(
            @PathVariable UUID id
    ){
        deleteItemByIdUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
