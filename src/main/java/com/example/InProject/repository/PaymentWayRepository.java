package com.example.InProject.repository;

import com.example.InProject.model.PaymentWayModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentWayRepository extends JpaRepository<PaymentWayModel, UUID> {
}
