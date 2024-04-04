package phong.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import phong.productservice.entity.ProductEntity;

public interface ProductRepository  extends MongoRepository<ProductEntity,String> {

}
