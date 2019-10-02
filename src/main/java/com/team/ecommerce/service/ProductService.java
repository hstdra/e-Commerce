package com.team.ecommerce.service;

import com.team.ecommerce.entity.Product;
import com.team.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> listAll() {
        return repository.findAll();
    }

    public Product get(int id) {
        return repository.getOne(id);
    }

    public void save(Product product) {
        repository.save(product);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
