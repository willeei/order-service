package rpe.tech.order.service.infrastructure.checkout.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemJpaEntity, String> {

    List<OrderItemJpaEntity> findByOrderId(String orderId);
}
