package com.HyperSrot.Shopping.App.service.serviceInterface;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IPaymentService {
    ResponseEntity<?> processPayment(Long userId, Long orderId, double amount);
}
