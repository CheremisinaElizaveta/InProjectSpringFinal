package com.example.InProject.service;

import com.example.InProject.model.PaymentWayModel;
import com.example.InProject.repository.PaymentWayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryPaymentWayService implements PaymentWayService {

    private final PaymentWayRepository paymentWayRepository;

    @Autowired
    public InMemoryPaymentWayService(PaymentWayRepository paymentWayRepository) {
        this.paymentWayRepository = paymentWayRepository;
    }

    @Override
    public List<PaymentWayModel> findAllPaymentWays() {
        return paymentWayRepository.findAll(Sort.by("id"));
    }

    @Override
    public PaymentWayModel findPaymentWayById(UUID id) {
        return paymentWayRepository.findById(id).orElse(null);
    }

    @Override
    public PaymentWayModel addPaymentWay(PaymentWayModel payment) {
        return paymentWayRepository.save(payment);
    }

    @Override
    public PaymentWayModel updatePaymentWay(PaymentWayModel payment) {
        if (paymentWayRepository.existsById(payment.getId())) {
            return paymentWayRepository.save(payment);
        }
        return null;
    }

    @Override
    public void deletePaymentWay(UUID id) {
        if (paymentWayRepository.existsById(id)) {
            paymentWayRepository.deleteById(id);
        }
    }
}
