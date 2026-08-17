package org.example.ecommerce.api.swagger.annotations.responses;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.ecommerce.shared.communication.response.ResponseErrorJson;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(
        responseCode = "400",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ResponseErrorJson.class)
        )
)
public @interface BadRequestResponse {
}
