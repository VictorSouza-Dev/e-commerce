package org.example.ecommerce.shared.communication.response.item;

import org.example.ecommerce.shared.communication.response.ResponseCategoryJson;

import java.util.UUID;

public class ResponseItemSummaryJson {
    private UUID id;
    private String title;
    private String description;
    private int price;
    private int quantity;
    private ResponseCategoryJson category;
    private String imageUrl;
}
