package com.example.InProject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductModel {
    @Id
    @GeneratedValue
    private UUID id;
    @Size(min = 3, message = "Название продукта не менее трех символов")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

    @ManyToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderModel> order;

    public ProductModel(){}

    public ProductModel(UUID id, String name, CategoryModel category, List<OrderModel> order) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.order = order;
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

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public List<OrderModel> getOrder() {
        return order;
    }

    public void setOrder(List<OrderModel> order) {
        this.order = order;
    }
}
