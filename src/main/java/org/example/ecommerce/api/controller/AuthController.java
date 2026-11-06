package org.example.ecommerce.api.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.ecommerce.shared.communication.swagger.annotations.responses.BadRequestResponse;
import org.example.ecommerce.application.useCases.login.doLogin.DoLoginUseCase;
import org.example.ecommerce.shared.communication.request.user.RequestLoginJson;
import org.example.ecommerce.shared.communication.response.user.ResponseRegisteredUserJson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
@Tag(name = "auth")
public class AuthController {

    private final DoLoginUseCase doLoginUseCase;

    public AuthController(
            DoLoginUseCase doLoginUseCase
    ) {
        this.doLoginUseCase = doLoginUseCase;
    }

    @PostMapping("/login")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseRegisteredUserJson.class)
            )
    )
    @BadRequestResponse
    public ResponseEntity<ResponseRegisteredUserJson> login(
            @Valid @RequestBody RequestLoginJson request
    ){
        var response = doLoginUseCase.execute(request);

        var cookie = ResponseCookie.from("access_Token",
                response.getTokens().getAccessToken())
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(Duration.ofHours(1))
                .build();


        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(response);
    }
}
