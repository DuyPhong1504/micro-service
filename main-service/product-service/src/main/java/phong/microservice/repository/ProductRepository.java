package phong.microservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import phong.microservice.entity.ProductEntity;

public interface ProductRepository  extends MongoRepository<ProductEntity,String> {

}
