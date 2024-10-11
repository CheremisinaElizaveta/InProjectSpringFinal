package com.example.InProject.controller;

import com.example.InProject.model.ProductModel;
import com.example.InProject.service.CategoryService;
import com.example.InProject.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/products")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        List<ProductModel> products = productService.findAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("product", new ProductModel());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "productList";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") ProductModel product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("products", productService.findAllProducts());
            model.addAttribute("categories", categoryService.findAllCategories());
            return "productList";
        }
        productService.addProduct(product);
        return "redirect:/products/all";
    }

    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute("product") ProductModel product, BindingResult result) {
        if (result.hasErrors()) {
            return "productList";
        }
        productService.updateProduct(product);
        return "redirect:/products/all";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam UUID id) {
        productService.deleteProduct(id);
        return "redirect:/products/all";
    }

    @GetMapping("/all/{id}")
    public String getIdProduct(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("product", productService.findProductById(id));
        model.addAttribute("categories", categoryService.findAllCategories());
        return "productList";
    }
}
