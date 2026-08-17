package org.example.ecommerce.shared.communication.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestLoginJson {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
