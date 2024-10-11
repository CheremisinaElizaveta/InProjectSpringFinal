package com.example.InProject.service;

import com.example.InProject.model.OrderModel;
import com.example.InProject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryOrderService implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public InMemoryOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderModel> findAllOrders() {
        return orderRepository.findAll(Sort.by("id"));
    }

    @Override
    public OrderModel findOrderById(UUID id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public OrderModel addOrder(OrderModel order) {
        return orderRepository.save(order);
    }

    @Override
    public OrderModel updateOrder(OrderModel order) {
        if (orderRepository.existsById(order.getId())) {
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public void deleteOrder(UUID id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        }
    }
}

