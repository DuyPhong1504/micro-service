package phong.inventoryservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InventoryDto {
    private Long id;
    private String productCode;
    private Integer quantity;
}
