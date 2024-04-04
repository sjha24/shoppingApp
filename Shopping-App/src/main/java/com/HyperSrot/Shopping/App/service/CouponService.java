package com.HyperSrot.Shopping.App.service;

import com.HyperSrot.Shopping.App.model.Coupon;
import com.HyperSrot.Shopping.App.service.serviceInterface.ICouponService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class CouponService implements ICouponService {
    private final Map<String, Coupon> coupons = new HashMap<>();
    // To keep track of used coupons by user ID.
    private final Map<Long, String> usedCoupons = new HashMap<>();

    public CouponService() {
        coupons.put("OFF5", new Coupon("OFF5", 5));
        coupons.put("OFF10", new Coupon("OFF10", 10));
    }
    private final Map<Long, Set<String>> usedCouponsByUser = new HashMap<>();

    public Map<String, Integer> fetchCoupons() {
        Map<String, Integer> couponCodes = new HashMap<>();
        coupons.forEach((code, coupon) -> couponCodes.put(code, coupon.getDiscountPercentage()));
        return couponCodes;
    }

    public boolean isCouponValidAndUnused(long userId, String code) {
        if (!coupons.containsKey(code)) {
            return false;
        }

        // Check if the user has used any coupons before
        Set<String> usedCoupons = usedCouponsByUser.get(userId);
        if (usedCoupons == null) {
            // User has not used any coupons yet, so the coupon code is valid and unused for this user
            return true;
        }



        // Check if the specific coupon code has been used by this user
        return !usedCoupons.contains(code);
    }
    public void markCouponAsUsedByUser(long userId, String code) {
        usedCouponsByUser.computeIfAbsent(userId, k -> new HashSet<>()).add(code);
    }

    public void markCouponAsUsed(long userId, String code) {
        usedCoupons.put(userId, code);
    }

    public Coupon getCoupon(String code) {
        return coupons.get(code);
    }
}
