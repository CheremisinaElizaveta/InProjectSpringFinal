package com.example.InProject.service;

import com.example.InProject.model.StatusOrderModel;
import com.example.InProject.repository.StatusOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryStatusOrderService implements StatusOrderService {

    private final StatusOrderRepository orderStatusRepository;

    @Autowired
    public InMemoryStatusOrderService(StatusOrderRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public List<StatusOrderModel> findAllOrderStatuses() {
        return orderStatusRepository.findAll(Sort.by("id"));
    }

    @Override
    public StatusOrderModel findOrderStatusById(UUID id) {
        return orderStatusRepository.findById(id).orElse(null);
    }

    @Override
    public StatusOrderModel addOrderStatus(StatusOrderModel status) {
        return orderStatusRepository.save(status);
    }

    @Override
    public StatusOrderModel updateOrderStatus(StatusOrderModel status) {
        if (orderStatusRepository.existsById(status.getId())) {
            return orderStatusRepository.save(status);
        }
        return null;
    }

    @Override
    public void deleteOrderStatus(UUID id) {
        if (orderStatusRepository.existsById(id)) {
            orderStatusRepository.deleteById(id);
        }
    }
}

