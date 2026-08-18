package org.example.ecommerce.shared.communication.response;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Builder
@Data
public class ResponseCategoryJson {
    private UUID id;
    private String name;
}
