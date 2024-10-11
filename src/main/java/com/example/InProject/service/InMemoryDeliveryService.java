package com.example.InProject.service;

import com.example.InProject.model.DeliveryModel;
import com.example.InProject.repository.DeliveryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryDeliveryService implements DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public InMemoryDeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public List<DeliveryModel> findAllDeliveries() {
        return deliveryRepository.findAll(Sort.by("id"));
    }

    @Override
    public DeliveryModel findDeliveryById(UUID id) {
        return deliveryRepository.findById(id).orElse(null);
    }

    @Override
    public DeliveryModel addDelivery(DeliveryModel delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public DeliveryModel updateDelivery(DeliveryModel delivery) {
        if (deliveryRepository.existsById(delivery.getId())) {
            return deliveryRepository.save(delivery);
        }
        return null;
    }

    @Override
    public void deleteDelivery(UUID id) {
        if (deliveryRepository.existsById(id)) {
            deliveryRepository.deleteById(id);
        }
    }
}

