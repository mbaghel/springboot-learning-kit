package com.springboot.learning.kit.repository;

import com.springboot.learning.kit.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    /**
     * Find all items associated with an order by order ID
     * @param orderId the order ID to search for
     * @return a list of items
     */
    List<OrderItem> findByOrderId(Long orderId);
}
