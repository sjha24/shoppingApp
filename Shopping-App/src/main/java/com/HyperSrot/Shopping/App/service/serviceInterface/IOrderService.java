package com.HyperSrot.Shopping.App.service.serviceInterface;

import com.HyperSrot.Shopping.App.DTO.OrderResponse;
import com.HyperSrot.Shopping.App.exception.CouponNotFoundException;
import com.HyperSrot.Shopping.App.exception.UserNotFoundException;
import com.HyperSrot.Shopping.App.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderService {
    ResponseEntity<?> placeOrder(long userId, int qty, String coupon) throws CouponNotFoundException, UserNotFoundException;

    List<OrderResponse> getOrdersByUserId(long userId);

    List<OrderResponse> getOrderDetails(long userId, long orderId);
}
