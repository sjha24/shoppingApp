package com.HyperSrot.Shopping.App.service;

import com.HyperSrot.Shopping.App.model.Order;
import com.HyperSrot.Shopping.App.model.Payment;
import com.HyperSrot.Shopping.App.repository.IOrderRepository;
import com.HyperSrot.Shopping.App.service.serviceInterface.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Random;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    IOrderRepository orderRepository;
    @Override
    public ResponseEntity<?> processPayment(Long userId, Long orderId, double amount) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Random random = new Random();
        int outcome = random.nextInt(6); // Randomly choose an outcome

        String transactionId = "tran" + String.format("%09d", random.nextInt(1000000000));
        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setOrder(order);
        payment.setTransactionId(transactionId);
        assert order != null;
        order.setTransactionId(payment.getTransactionId());
        order.setDate(LocalDate.now());
        if(outcome == 0) {
            payment.setStatus("Successful");
        }else{
            payment.setStatus("Failed");
        }
        order.setStatus(payment.getStatus());
        orderRepository.save(order);

        return switch (outcome) {
            case 0 -> ResponseEntity.ok(payment);
            case 1 -> {
                payment.setDescription("Payment Failed as amount is invalid");
                yield ResponseEntity.badRequest().body(payment);
            }
            case 2 -> {
                payment.setDescription("Payment Failed from bank");
                yield ResponseEntity.badRequest().body(payment);
            }
            case 3 -> {
                payment.setDescription("Payment Failed due to invalid order id");
                yield ResponseEntity.badRequest().body(payment);
            }
            case 4 -> {
                payment.setDescription("No response from payment server");
                yield ResponseEntity.status(504).body(payment);
            }
            case 5 -> {
                payment.setDescription("Order is already paid for");
                yield ResponseEntity.status(405).body(payment);
            }
            default -> {
                payment.setDescription("Unexpected error occurred");
                yield ResponseEntity.internalServerError().body(payment);
                // Default case to handle unexpected outcomes
            }
        };
    }
}
