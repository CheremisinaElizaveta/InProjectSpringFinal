package com.example.InProject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orderstatuses")
public class StatusOrderModel {
    @Id
    @GeneratedValue
    private UUID id;
    @Size(min = 3, message = "Название не менее трех символов")
    private String name;

    @ManyToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<OrderModel> order;


    public StatusOrderModel(){}

    public StatusOrderModel(UUID id, String name, List<OrderModel> order) {
        this.id = id;
        this.name = name;
        this.order = order;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @Size(min = 3, message = "Название не менее трех символов") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, message = "Название не менее трех символов") String name) {
        this.name = name;
    }

    public List<OrderModel> getOrder() {
        return order;
    }

    public void setOrder(List<OrderModel> order) {
        this.order = order;
    }
}
