package com.example.InProject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class CategoryModel {
    @Id
    @GeneratedValue
    private UUID id;
    @Size(min = 3, message = "Название продукта не менее трех символов")
    private String name;

    @ManyToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ProductModel> products;

    public CategoryModel(){}

    public CategoryModel(UUID id, String name, List<ProductModel> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @Size(min = 3, message = "Название продукта не менее трех символов") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, message = "Название продукта не менее трех символов") String name) {
        this.name = name;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}
