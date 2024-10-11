package com.example.InProject.service;

import com.example.InProject.model.CategoryModel;
import com.example.InProject.repository.CategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;

    public InMemoryCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryModel> findAllCategories() {
        return categoryRepository.findAll(Sort.by("id")); // Сортировка по ID для удобства
    }

    @Override
    public CategoryModel findCategoryById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public CategoryModel addCategory(CategoryModel category) {
        return categoryRepository.save(category);
    }

    @Override
    public CategoryModel updateCategory(CategoryModel category) {
        if (categoryRepository.existsById(category.getId())) {
            return categoryRepository.save(category);
        }
        return null;
    }

    @Override
    public void deleteCategory(UUID id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }
    }
}


