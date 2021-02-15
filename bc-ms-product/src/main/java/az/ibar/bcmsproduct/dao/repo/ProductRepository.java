package az.ibar.bcmsproduct.dao.repo;

import az.ibar.bcmsproduct.dao.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    Page<ProductEntity> findProductEntitiesByUserIdAndInventoryIsGreaterThanEqual(@Param("userId") Long userId,Long inventoryCount, Pageable pageable);

}
