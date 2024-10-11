package com.example.InProject.service;

import com.example.InProject.model.OrderModel;
import com.example.InProject.model.PaymentWayModel;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    public List<OrderModel> findAllOrders();
    public OrderModel findOrderById(UUID id);
    public OrderModel addOrder(OrderModel order);
    public OrderModel updateOrder(OrderModel order);
    public void deleteOrder(UUID id);
}
