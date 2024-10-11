package com.example.InProject.service;

import com.example.InProject.model.ProductModel;
import com.example.InProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryProductService implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public InMemoryProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductModel> findAllProducts() {
        return productRepository.findAll(Sort.by("id"));
    }

    @Override
    public ProductModel findProductById(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public ProductModel addProduct(ProductModel product) {
        return productRepository.save(product);
    }

    @Override
    public ProductModel updateProduct(ProductModel product) {
        if (productRepository.existsById(product.getId())) {
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public void deleteProduct(UUID id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }
}
