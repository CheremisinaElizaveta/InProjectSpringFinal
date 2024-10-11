package com.example.InProject.controller;

import com.example.InProject.model.CategoryModel;
import com.example.InProject.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@RequestMapping("/categories")
@PreAuthorize("hasAnyAuthority('MANAGER')")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @GetMapping("/all")
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("category", new CategoryModel());
        return "categoriesList";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") CategoryModel category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAllCategories());
            return "categoriesList";
        }
        categoryService.addCategory(category);
        return "redirect:/categories/all";
    }

    @PostMapping("/update")
    public String updateCategory(@Valid @ModelAttribute("category") CategoryModel category, BindingResult result) {
        categoryService.updateCategory(category);
        return "redirect:/categories/all";
    }

    @PostMapping("/delete")
    public String deleteCategory(@RequestParam UUID id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories/all";
    }

    @GetMapping("/all/{id}")
    public String getIdCategory(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("categories", categoryService.findCategoryById(id));
        model.addAttribute("category", new CategoryModel());
        return "categoriesList";
    }

}
