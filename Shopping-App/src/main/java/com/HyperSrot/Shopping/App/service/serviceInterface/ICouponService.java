package com.HyperSrot.Shopping.App.service.serviceInterface;

import com.HyperSrot.Shopping.App.exception.CouponNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ICouponService {
    Map<String, Integer> fetchCoupons();

    boolean isCouponValidAndUnused(long userId, String couponCode);

    Object getCoupon(String couponCode) throws CouponNotFoundException;

    void markCouponAsUsed(long userId, String couponCode);
    void markCouponAsUsedByUser(long userId, String code);
}
