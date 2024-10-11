package com.example.InProject.repository;

import com.example.InProject.model.DeliveryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<DeliveryModel, UUID> {
}
