package org.example.ecommerce.api.controller;


import jakarta.validation.Valid;
import org.apache.coyote.Response;
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
    public ResponseEntity<ResponseRegisteredItemJson> register(
            @Valid  @RequestPart("data") RequestItemJson request,
            @RequestPart("image") MultipartFile image
    ) throws IOException {
        var response = registerItemUseCase.execute(request, image.getBytes());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRegisteredItemJson> getItem(
            @PathVariable UUID id
    ){
        var response = getItemByIdUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(
            @PathVariable UUID id
    ){
        deleteItemByIdUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
