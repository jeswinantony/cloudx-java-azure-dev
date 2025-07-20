package com.chtrembl.petstoreapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemsReserverService {

    private final static String QUEUE_NAME = "orders";

    private final JmsTemplate jmsTemplate;

    public void updateOrderItems(String sessionId, String orderJson) {
        log.info("Order items reserver service called with sessionId: {} and orderJson: {}", sessionId, orderJson);

        jmsTemplate.convertAndSend(
                QUEUE_NAME,
                Map.of(
                        "sessionId", sessionId,
                        "orderJson", orderJson
                )
        );
    }
}
