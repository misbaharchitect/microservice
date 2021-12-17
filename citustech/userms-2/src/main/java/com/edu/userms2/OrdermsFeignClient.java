package com.edu.userms2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("orderms")
public interface OrdermsFeignClient {
    @RequestMapping(
            method= RequestMethod.GET,
            value="/orders",
            consumes="application/json")
    Object getOrders();
}
