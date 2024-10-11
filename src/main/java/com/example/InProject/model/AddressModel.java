package com.example.InProject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "addresses")
public class AddressModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String house;
    @Size(min = 6, message = "Название улицы не менее шести символов")
    private String street;
    @Size(min = 3, message = "Название города трех символов")
    private String city;
    @Size(min = 3, message = "Название области не менее трех символов")
    private String state;
    private String postalCode;
    @Size(min = 3, message = "Название страны трех символов")
    private String country;

    @OneToOne(optional = false, mappedBy = "address" )
    private OrderModel owner;

    public AddressModel(){}

    public AddressModel(UUID id, String house, String street, String city, String state, String postalCode, String country, OrderModel owner) {
        this.id = id;
        this.house = house;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.owner = owner;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getHouse() {
        return house;
    }

    public void setHouse( String house) {
        this.house = house;
    }

    public @Size(min = 6, message = "Название улицы не менее шести символов") String getStreet() {
        return street;
    }

    public void setStreet(@Size(min = 6, message = "Название улицы не менее шести символов") String street) {
        this.street = street;
    }

    public @Size(min = 3, message = "Название города трех символов") String getCity() {
        return city;
    }

    public void setCity(@Size(min = 3, message = "Название города трех символов") String city) {
        this.city = city;
    }

    public @Size(min = 3, message = "Название области не менее трех символов") String getState() {
        return state;
    }

    public void setState(@Size(min = 3, message = "Название области не менее трех символов") String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public @Size(min = 3, message = "Название страны трех символов") String getCountry() {
        return country;
    }

    public void setCountry(@Size(min = 3, message = "Название страны трех символов") String country) {
        this.country = country;
    }

    public OrderModel getOwner() {
        return owner;
    }

    public void setOwner(OrderModel owner) {
        this.owner = owner;
    }
}
