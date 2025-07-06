package com.chtrembl.petstoreapp.client;

import com.chtrembl.petstoreapp.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "order-items-reserver-service",
        url = "${petstore.service.order-items-reserver.url}",
        configuration = FeignConfig.class
)
public interface OrderItemsReserverClient {
    @PostMapping("/api/orderitemsreserver")
    void updateOrderItems(@RequestParam("sessionId") String sessionId, String orderJson);
}
