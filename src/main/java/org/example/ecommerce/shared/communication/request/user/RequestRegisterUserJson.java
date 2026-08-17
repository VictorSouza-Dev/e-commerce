package org.example.ecommerce.shared.communication.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestRegisterUserJson {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Size(min = 6, max = 50)
    private String password;

    @NotBlank
    private String phone;
}
