package phong.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryResponse {
    private String productCode;
    private boolean isInStock;
}
