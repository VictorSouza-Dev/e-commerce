package org.example.ecommerce.api.controller;

import jakarta.validation.Valid;
import org.example.ecommerce.application.useCases.login.doLogin.DoLoginUseCase;
import org.example.ecommerce.shared.communication.request.user.RequestLoginJson;
import org.example.ecommerce.shared.communication.response.user.ResponseRegisteredUserJson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final DoLoginUseCase doLoginUseCase;

    public AuthController(
            DoLoginUseCase doLoginUseCase
    ) {
        this.doLoginUseCase = doLoginUseCase;
    }

    @PostMapping
    public ResponseEntity<ResponseRegisteredUserJson> login(
            @Valid @RequestBody RequestLoginJson request
    ){
        var response = doLoginUseCase.execute(request);

        return ResponseEntity.ok(response);
    }
}
