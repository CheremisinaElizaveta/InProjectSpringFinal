package com.example.InProject.service;

import com.example.InProject.model.CourierModel;
import com.example.InProject.model.DeliveryModel;

import java.util.List;
import java.util.UUID;

public interface CourierService {
    public List<CourierModel> findAllCouriers();
    public CourierModel findCourierById(UUID id);
    public CourierModel addCourier(CourierModel courier);
    public CourierModel updateCourier(CourierModel courier);
    public void deleteCourier(UUID id);
}
