package org.example.ecommerce.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.ecommerce.application.useCases.userProfile.register.RegisterUserProfileUseCase;
import org.example.ecommerce.shared.communication.request.userProfile.RequestRegisterUserProfileJson;
import org.example.ecommerce.shared.communication.response.user.ResponseUserProfileJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userprofile")
@Tag(name = "")
public class UserProfileController {

    private final RegisterUserProfileUseCase registerUserProfileUseCase;

    public UserProfileController(
            RegisterUserProfileUseCase registerUserProfileUseCase1
    ){
        this.registerUserProfileUseCase = registerUserProfileUseCase1;
    }

    @PostMapping()
    public ResponseEntity<ResponseUserProfileJson> getUserProfile(
            @Valid @RequestBody RequestRegisterUserProfileJson request
    ){
        var response = this.registerUserProfileUseCase.execute(request);

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
