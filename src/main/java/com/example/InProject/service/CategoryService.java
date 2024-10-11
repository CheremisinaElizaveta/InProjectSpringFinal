package com.example.InProject.service;

import com.example.InProject.model.CategoryModel;
import com.example.InProject.model.CourierModel;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    public List<CategoryModel> findAllCategories();
    public CategoryModel findCategoryById(UUID id);
    public CategoryModel addCategory(CategoryModel category);
    public CategoryModel updateCategory(CategoryModel category);
    public void deleteCategory(UUID id);
}
