package com.HyperSrot.Shopping.App.controller;

import com.HyperSrot.Shopping.App.service.serviceInterface.ICouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "Coupon Management")
public class CouponController {
    @Autowired
    private ICouponService couponService;
    @Operation(description = "Retrieves a list of available coupons along with their discount percentages.")
    @ApiResponse(responseCode = "200",description = ": Successfully retrieved the list of coupons. Returns a map of coupon codes and their corresponding discount percentages")
    @GetMapping("/fetchCoupons")
    public ResponseEntity<Map<String, Integer>> fetchCoupons() {
        return ResponseEntity.ok(couponService.fetchCoupons());
    }

}
