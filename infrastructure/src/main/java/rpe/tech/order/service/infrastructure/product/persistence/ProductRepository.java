package rpe.tech.order.service.infrastructure.product.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, String> {

    Page<ProductJpaEntity> findAll(Specification<ProductJpaEntity> whereClause, Pageable page);

    @Query(value = "SELECT p.id FROM Product p WHERE p.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);
}
