package org.example.ecommerce.shared.communication.response.item;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseRegisteredItemJson {
    private UUID publicId;
    private String name;
    private String description;
    private String category;
    private String longDescription;
    private String imageUrl;
}
