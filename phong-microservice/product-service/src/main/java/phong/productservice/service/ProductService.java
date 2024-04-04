package phong.productservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import phong.productservice.dto.ProductDto;
import phong.productservice.entity.ProductEntity;
import phong.productservice.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public boolean createProduct(ProductDto dto) {
        ProductEntity productEntity = ProductEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
        return !ObjectUtils.isEmpty(productRepository.save(productEntity));
    }

    public List<ProductDto> getAllProduct() {
        List<ProductEntity> listProduct = productRepository.findAll();
        List<ProductDto> listResult = new ArrayList<>();
        ProductDto dto;
        for (ProductEntity entity : listProduct) {
            dto = new ProductDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDescription(entity.getDescription());
            listResult.add(dto);
        }
        return listResult;
    }

    public boolean deleteProductById(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateProductbyId(ProductDto dto) {
        if (productRepository.existsById(dto.getId())) {
            ProductEntity entity = ProductEntity.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .price(dto.getPrice())
                    .description(dto.getDescription()).build();
            productRepository.save(entity);
            return true;
        }
        return false;
    }
}
