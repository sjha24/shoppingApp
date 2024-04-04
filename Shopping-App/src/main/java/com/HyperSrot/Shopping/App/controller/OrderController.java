package com.HyperSrot.Shopping.App.controller;

import com.HyperSrot.Shopping.App.DTO.OrderResponse;
import com.HyperSrot.Shopping.App.exception.CouponNotFoundException;
import com.HyperSrot.Shopping.App.exception.UserNotFoundException;
import com.HyperSrot.Shopping.App.model.Order;
import com.HyperSrot.Shopping.App.service.serviceInterface.IOrderService;
import com.HyperSrot.Shopping.App.service.serviceInterface.IPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order Management", description = "User Can Place Order, Process Payment and get Order Details")
public class OrderController {
    @Autowired
    IOrderService orderService;
    @Autowired
    IPaymentService paymentService;
    @Operation(summary = "Allows a user to place an order for a specified quantity of products. Optionally, a coupon code can be applied to receive a discount")
    @ApiResponse(responseCode = "200", description = "Order placed successfully. Returns the order details including applied discount")
    @ApiResponse(responseCode = "404", description = "User or coupon not found.!!")
    @ApiResponse(responseCode = "400", description = "Invalid quantity or coupon code specified")
    @PostMapping("/{userId}/order")
    public ResponseEntity<?> placeOrder(@PathVariable long userId, @RequestParam int qty, @RequestParam(required = false) String coupon) throws CouponNotFoundException, UserNotFoundException {
        return orderService.placeOrder(userId, qty, coupon);
    }

    @Operation(summary = "Processes payment for an order by a specified user")
    @ApiResponse(responseCode = "200", description = "Payment successful. Returns payment details including transaction ID and status")
    @ApiResponse(responseCode = "400",description = "Payment failed due to various reasons such as invalid amount, failed bank transaction, or invalid order ID")
    @ApiResponse(responseCode = "405",description = "Order already paid for")
    @ApiResponse(responseCode = "504",description = "No response from payment server")
    @PostMapping("/{userId}/{orderId}/pay")
    public ResponseEntity<?>processPayment(@PathVariable Long userId,@PathVariable Long orderId,@RequestParam double amount){
        return paymentService.processPayment(userId,orderId,amount);
    }

    @Operation(summary = "Retrieves all orders placed by a specified user")
    @ApiResponse(responseCode = "200",description = "Successfully retrieved user orders. Returns a list of orders")
    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@PathVariable long userId) {
        List<OrderResponse> userOrders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(userOrders);
    }

    @Operation(summary = "Retrieves details for a specific order placed by a user.")
    @ApiResponse(responseCode = "200",description = " Successfully retrieved order details. Returns details of the specific order including transaction status")
    @ApiResponse(responseCode = "404",description = "Order Not Found")
    @GetMapping("/{userId}/orders/{orderId}")
    public ResponseEntity<?> getOrderDetails(@PathVariable long userId, @PathVariable long orderId) {
        List<OrderResponse> orderDetails = orderService.getOrderDetails(userId, orderId);
        if (orderDetails.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "orderId", orderId,
                    "description", "Order not found"
            ));
        }
        return ResponseEntity.ok(orderDetails);
    }

}
