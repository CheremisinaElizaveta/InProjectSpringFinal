package com.example.InProject.service;

import com.example.InProject.model.StatusOrderModel;

import java.util.List;
import java.util.UUID;

public interface StatusOrderService {
    public List<StatusOrderModel> findAllOrderStatuses();
    public StatusOrderModel findOrderStatusById(UUID id);
    public StatusOrderModel addOrderStatus(StatusOrderModel status);
    public StatusOrderModel updateOrderStatus(StatusOrderModel status);
    public void deleteOrderStatus(UUID id);
}
