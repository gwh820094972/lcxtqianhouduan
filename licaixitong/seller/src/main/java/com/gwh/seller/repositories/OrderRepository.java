package com.gwh.seller.repositories;

import com.gwh.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 订单管理
 */
public interface OrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {
    List<Order> findOrderTByChanUserId(String id);
    Order findOrderTByOrderId(String orderId);

//    List<Order> findOrderTByChanUserIdAndOrderType(String id ,String orderType);
}
