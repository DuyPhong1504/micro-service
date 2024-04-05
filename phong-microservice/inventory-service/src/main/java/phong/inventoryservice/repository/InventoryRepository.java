package phong.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phong.inventoryservice.entity.InventoryEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity,Long> {
    List<InventoryEntity> findByProductCodeIn(List<String> listProductCode);
}
