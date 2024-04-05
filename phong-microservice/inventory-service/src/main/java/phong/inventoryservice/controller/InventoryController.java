package phong.inventoryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import phong.inventoryservice.dto.InventoryResponse;
import phong.inventoryservice.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> check(@RequestParam List<String> listProductCode){
        return inventoryService.isInStock(listProductCode);
    }
}
