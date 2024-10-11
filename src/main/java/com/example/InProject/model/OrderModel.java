package com.example.InProject.model;

import com.example.InProject.converter.DateConverter;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String date;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressModel address;

    @ManyToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<DeliveryModel> deliveries;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusOrderModel status;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentWayModel payment;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;

    public OrderModel(){}

    public OrderModel(UUID id, String name, String date, AddressModel address, List<DeliveryModel> deliveries, StatusOrderModel status, PaymentWayModel payment, ProductModel product) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.address = address;
        this.deliveries = deliveries;
        this.status = status;
        this.payment = payment;
        this.product = product;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public List<DeliveryModel> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryModel> deliveries) {
        this.deliveries = deliveries;
    }

    public StatusOrderModel getStatus() {
        return status;
    }

    public void setStatus(StatusOrderModel status) {
        this.status = status;
    }

    public PaymentWayModel getPayment() {
        return payment;
    }

    public void setPayment(PaymentWayModel payment) {
        this.payment = payment;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }
}
