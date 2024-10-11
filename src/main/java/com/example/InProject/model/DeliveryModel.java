package com.example.InProject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "deliveries")
public class DeliveryModel {
    @Id
    @GeneratedValue
    private UUID id;
    @Size(max = 255, message = "Дополнительная информация не более 255-ти символов")
    private String info;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private CourierModel courier;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    public DeliveryModel(){}

    public DeliveryModel(UUID id, String info, CourierModel courier, OrderModel order) {
        this.id = id;
        this.info = info;
        this.courier = courier;
        this.order = order;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @Size(max = 255, message = "Дополнительная информация не более 255-ти символов") String getInfo() {
        return info;
    }

    public void setInfo(@Size(max = 255, message = "Дополнительная информация не более 255-ти символов") String info) {
        this.info = info;
    }

    public CourierModel getCourier() {
        return courier;
    }

    public void setCourier(CourierModel courier) {
        this.courier = courier;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }
}
