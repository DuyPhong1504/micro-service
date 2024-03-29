package phong.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import phong.microservice.dto.ProductDto;
import phong.microservice.entity.ProductEntity;
import phong.microservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.getAllProduct();
    }

    @PostMapping
    public boolean createProduct(@RequestBody ProductDto dto) {
        return productService.createProduct(dto);
    }

    @PutMapping
    public boolean updateProductById(@RequestBody ProductDto dto){
        return productService.updateProductbyId(dto);
    }

    @DeleteMapping
    public boolean deleteById(@RequestBody String id){
        return productService.deleteProductById(id);
    }

}
