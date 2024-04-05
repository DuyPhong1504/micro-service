package phong.inventoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phong.inventoryservice.dto.InventoryResponse;
import phong.inventoryservice.repository.InventoryRepository;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<InventoryResponse> isInStock(List<String> listProductCode){
        return inventoryRepository.findByProductCodeIn(listProductCode).
                stream().map(inventory ->
                    InventoryResponse.builder().productCode(inventory.getProductCode())
                            .isInStock(inventory.getQuantity() > 0 )
                            .build()
                ).toList();
    }
}
