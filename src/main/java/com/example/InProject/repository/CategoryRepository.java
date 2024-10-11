package com.example.InProject.repository;

import com.example.InProject.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryModel, UUID> {
}
