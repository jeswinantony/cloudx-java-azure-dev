package com.chtrembl.petstore.order.service;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.chtrembl.petstore.order.model.Order;
import org.springframework.stereotype.Repository;

@Repository
interface OrderRepository extends CosmosRepository<Order, String> {
}
