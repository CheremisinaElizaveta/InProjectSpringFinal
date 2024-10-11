package com.example.InProject.repository;

import com.example.InProject.model.ProductModel;
import com.example.InProject.model.StatusOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StatusOrderRepository extends JpaRepository<StatusOrderModel, UUID> {
}
