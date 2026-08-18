package org.example.ecommerce.shared.communication.response.item;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseItemsJson {
    private List<ResponseItemSummaryJson> items;
}
