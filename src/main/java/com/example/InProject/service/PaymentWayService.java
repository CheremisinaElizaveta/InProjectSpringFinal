package com.example.InProject.service;

import com.example.InProject.model.PaymentWayModel;
import com.example.InProject.model.ProductModel;

import java.util.List;
import java.util.UUID;

public interface PaymentWayService {
    public List<PaymentWayModel> findAllPaymentWays();
    public PaymentWayModel findPaymentWayById(UUID id);
    public PaymentWayModel addPaymentWay(PaymentWayModel payment);
    public PaymentWayModel updatePaymentWay(PaymentWayModel payment);
    public void deletePaymentWay(UUID id);
}
