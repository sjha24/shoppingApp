package com.HyperSrot.Shopping.App.repository;

import com.HyperSrot.Shopping.App.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IOrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByOrderIdAndUserUserId(long orderId, long userId);
}
