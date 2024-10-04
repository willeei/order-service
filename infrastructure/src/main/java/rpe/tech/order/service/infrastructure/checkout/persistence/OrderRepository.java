package rpe.tech.order.service.infrastructure.checkout.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rpe.tech.order.service.domain.checkout.OrderPreview;

public interface OrderRepository extends JpaRepository<OrderJpaEntity, String> {

    @Query("""
            select new rpe.tech.order.service.domain.checkout.OrderPreview(
                o.id as id,
                o.customer.id as customerId,
                o.opened as opened,
                o.createdAt as createdAt,
                o.deletedAt as deletedAt
            )
            from Order o
                join o.items items
            where
                ( :terms is null or UPPER(o.customer.id) like :terms )
            """)
    Page<OrderPreview> findAll(
            @Param("terms") String terms,
            Pageable page
    );
}
