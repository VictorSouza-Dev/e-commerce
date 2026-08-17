package org.example.ecommerce.shared.communication.request.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class RequestItemJson {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String longDescription;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    private UUID categoryId;

    private String newCategoryName;

    private String size;

    @NotBlank
    private String itemCondition;

    private String storageLocation;
}
