package org.example.ecommerce.shared.communication.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ecommerce.shared.communication.response.ResponseTokensJson;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseRegisteredUserJson {
    private String email;
    private ResponseTokensJson tokens;
}
