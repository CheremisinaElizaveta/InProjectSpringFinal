package com.example.InProject.service;

import com.example.InProject.model.DeliveryModel;
import com.example.InProject.model.OrderModel;

import java.util.List;
import java.util.UUID;

public interface DeliveryService {
    public List<DeliveryModel> findAllDeliveries();
    public DeliveryModel findDeliveryById(UUID id);
    public DeliveryModel addDelivery(DeliveryModel delivery);
    public DeliveryModel updateDelivery(DeliveryModel delivery);
    public void deleteDelivery(UUID id);
}
