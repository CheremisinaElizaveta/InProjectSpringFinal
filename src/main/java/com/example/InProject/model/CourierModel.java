package com.example.InProject.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "couriers")
public class CourierModel {
    @Id
    @GeneratedValue
    private UUID id;
    @Size(min = 2, message = "Имя не менее двух символов")
    private String firstName;
    @Size(min = 2, message = "Фамилия не менее двух символов")
    private String lastName;
    @Size(min = 3, message = "Отчество не менее трех символов")
    private String patronymic;
    private String phoneNumber;

    @ManyToMany(mappedBy = "courier", cascade = CascadeType.ALL)
    private List<DeliveryModel> deliveries;

    public CourierModel(){
    }

    public CourierModel(UUID id, String firstName, String lastName, String patronymic, String phoneNumber, List<DeliveryModel> deliveries) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.deliveries = deliveries;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @Size(min = 2, message = "Имя не менее двух символов") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 2, message = "Имя не менее двух символов") String firstName) {
        this.firstName = firstName;
    }

    public @Size(min = 2, message = "Фамилия не менее двух символов") String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(min = 2, message = "Фамилия не менее двух символов") String lastName) {
        this.lastName = lastName;
    }

    public @Size(min = 3, message = "Отчество не менее трех символов") String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(@Size(min = 3, message = "Отчество не менее трех символов") String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<DeliveryModel> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryModel> deliveries) {
        this.deliveries = deliveries;
    }
}
