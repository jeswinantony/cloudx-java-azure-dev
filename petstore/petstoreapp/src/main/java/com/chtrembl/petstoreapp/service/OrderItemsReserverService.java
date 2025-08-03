package com.chtrembl.petstoreapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void updateOrderItems(String sessionId, String orderJson) {
        log.info("Order items reserver service called with sessionId: {} and orderJson: {}", sessionId, orderJson);

        var payload = objectMapper.writeValueAsString(Map.of(
                "sessionId", sessionId,
                "orderJson", orderJson
        ));

        jmsTemplate.send(QUEUE_NAME, session -> {
            var message = session.createTextMessage();
            message.setStringProperty("contentType", "plain/text");
            message.setText(payload);
            return message;
        });
    }
}
