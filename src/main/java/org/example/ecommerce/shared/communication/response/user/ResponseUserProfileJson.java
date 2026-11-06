package org.example.ecommerce.shared.communication.response.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUserProfileJson {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String dateOfBirth;
}
