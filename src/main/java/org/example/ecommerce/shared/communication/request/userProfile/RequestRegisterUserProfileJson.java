package org.example.ecommerce.shared.communication.request.userProfile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RequestRegisterUserProfileJson {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Pattern(
            regexp = "\\d",
            message = "O telefone deve conter apenas números"
    )
    @NotBlank
    @Length(min = 11 , max = 13, message = "O telefone deve conter apenas numeros entre 10 ou 13 numeros")
    private String phoneNumber;
    @NotBlank
    private String dateOfBirth;
}
