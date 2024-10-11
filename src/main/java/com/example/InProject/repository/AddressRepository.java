package com.example.InProject.repository;

import com.example.InProject.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressModel, UUID> {
}
