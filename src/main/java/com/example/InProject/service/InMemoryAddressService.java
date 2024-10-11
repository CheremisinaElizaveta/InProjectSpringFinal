package com.example.InProject.service;

import com.example.InProject.model.AddressModel;
import com.example.InProject.repository.AddressRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryAddressService implements AddressService {
    private final AddressRepository addressRepository;

    public InMemoryAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressModel> findAllAddresses() {
        return addressRepository.findAll(Sort.by("id")); // Сортировка по ID для удобства
    }

    @Override
    public AddressModel findAddressById(UUID id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public AddressModel addAddress(AddressModel address) {
        return addressRepository.save(address);
    }

    @Override
    public AddressModel updateAddress(AddressModel address) {
        if (addressRepository.existsById(address.getId())) {
            return addressRepository.save(address);
        }
        return null;
    }

    @Override
    public void deleteAddress(UUID id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        }
    }
}


