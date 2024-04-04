package com.HyperSrot.Shopping.App.service;
import com.HyperSrot.Shopping.App.DTO.OrderResponse;
import com.HyperSrot.Shopping.App.exception.CouponNotFoundException;
import com.HyperSrot.Shopping.App.exception.UserNotFoundException;
import com.HyperSrot.Shopping.App.model.Coupon;
import com.HyperSrot.Shopping.App.model.Order;
import com.HyperSrot.Shopping.App.model.User;
import com.HyperSrot.Shopping.App.repository.IOrderRepository;
import com.HyperSrot.Shopping.App.repository.IUserRepository;
import com.HyperSrot.Shopping.App.service.serviceInterface.ICouponService;
import com.HyperSrot.Shopping.App.service.serviceInterface.IInventoryService;
import com.HyperSrot.Shopping.App.service.serviceInterface.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {

    private final IInventoryService inventoryService;
    private final ICouponService couponService;
    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;
    @Override
    public ResponseEntity<?> placeOrder(long userId, int qty, String couponCode) throws CouponNotFoundException, UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("User Is Not Found"));

        // Validate quantity
        if (qty < 1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"description\": \"Invalid quantity\"}");
        }

        // Check inventory availability
        if (!inventoryService.checkInventoryAvailability(qty)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"description\": \"Not enough inventory\"}");
        }

        // Validate coupon
        if (couponCode != null && !couponService.isCouponValidAndUnused(user.getUserId(), couponCode)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"description\": \"Invalid coupon\"}");
        }

        // Calculate amount
        double pricePerUnit = inventoryService.getPricePerUnit();
        double totalAmount = qty * pricePerUnit;
        totalAmount = calculateDiscountedAmount(totalAmount,couponCode);


        // Deduct from inventory
        inventoryService.deductInventory(qty);

        // Mark coupon as used
        if (couponCode != null) {
            couponService.markCouponAsUsed(user.getUserId(), couponCode);
        }


        // Create order

        Order order = new Order(qty, totalAmount, couponCode);
        order.setUser(user);
        if(order.getDate() == null){
            order.setDate(LocalDate.now());
        }
        orderRepository.save(order);

        //orderResponse
        OrderResponse orderResponse = new OrderResponse(order.getOrderId(),user.getUserId(),
                order.getQuantity(),order.getAmount(),couponCode);
        couponService.markCouponAsUsedByUser(userId,couponCode);
        return ResponseEntity.ok(orderResponse);
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(long userId) {

        List<Order>orderList = userRepository.findById(userId).get().getOrderList();
        return orderList.stream().map(order -> {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderId(order.getOrderId());
            orderResponse.setAmount(order.getAmount());
            orderResponse.setCoupon(order.getCoupon());
            orderResponse.setDate(order.getDate());
            return orderResponse;
        }).toList();
    }

    @Override
    public List<OrderResponse> getOrderDetails(long userId, long orderId) {
        List<Order>orderList = orderRepository.findByOrderIdAndUserUserId(orderId,userId);

        return orderList.stream().map(order -> {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderId(order.getOrderId());
            orderResponse.setAmount(order.getAmount());
            orderResponse.setCoupon(order.getCoupon());
            orderResponse.setDate(order.getDate());
            orderResponse.setTransactionId(order.getTransactionId());
            orderResponse.setStatus(order.getStatus());
            return orderResponse;
        }).toList();
    }


    private double calculateDiscountedAmount(double totalAmount, String couponCode) throws CouponNotFoundException {
        if (couponCode == null) {
            return totalAmount; // No discount applied
        }

        Coupon coupon = (Coupon) couponService.getCoupon(couponCode);
        if (coupon != null) {
            double discountPercentage = coupon.getDiscountPercentage();
            return totalAmount - (totalAmount * discountPercentage / 100);
        } else {
            return totalAmount; // No discount applied if coupon is invalid

        }
    }
}
