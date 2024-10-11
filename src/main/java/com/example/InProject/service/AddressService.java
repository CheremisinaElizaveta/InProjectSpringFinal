package com.example.InProject.service;

import com.example.InProject.model.AddressModel;
import com.example.InProject.model.CategoryModel;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    public List<AddressModel> findAllAddresses();
    public AddressModel findAddressById(UUID id);
    public AddressModel addAddress(AddressModel address);
    public AddressModel updateAddress(AddressModel address);
    public void deleteAddress(UUID id);
}
