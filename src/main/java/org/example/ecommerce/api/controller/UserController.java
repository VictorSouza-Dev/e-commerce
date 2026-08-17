package org.example.ecommerce.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.ecommerce.api.swagger.annotations.responses.BadRequestResponse;
import org.example.ecommerce.application.useCases.user.profile.GetUserProfileUseCase;
import org.example.ecommerce.application.useCases.user.register.RegisterUserUseCase;
import org.example.ecommerce.shared.communication.request.user.RequestRegisterUserJson;
import org.example.ecommerce.shared.communication.response.user.ResponseRegisteredUserJson;
import org.example.ecommerce.shared.communication.response.user.ResponseUserProfileJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "user")
public class UserController {

    private final RegisterUserUseCase registerUseCase;
    private final GetUserProfileUseCase getUserProfileUseCase;

    public UserController(
            RegisterUserUseCase registerUseCase,
            GetUserProfileUseCase getUserProfileUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.getUserProfileUseCase = getUserProfileUseCase;
    }

    @Operation(
            summary = "get user logged"
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                mediaType = "application/json",
                    schema = @Schema(implementation = ResponseUserProfileJson.class)
            )
    )
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ResponseUserProfileJson> getUserProfile(){
        var response = getUserProfileUseCase.execute();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiResponse(
           responseCode = "201",
            content = @Content(
                    mediaType = "application/json",
                    schema =  @Schema(implementation = ResponseUserProfileJson.class)
            )
    )
    @BadRequestResponse
    @PostMapping
    public ResponseEntity<ResponseRegisteredUserJson> add(
            @Valid @RequestBody RequestRegisterUserJson request
    ){
        var response = registerUseCase.execute(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
