package com.example.InProject.repository;

import com.example.InProject.model.CourierModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourierRepository extends JpaRepository<CourierModel, UUID> {
}
