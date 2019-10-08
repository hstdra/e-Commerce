package com.team.ecommerce.service;

import com.team.ecommerce.entity.Product;
import com.team.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAll(Pageable pageable) {
        return repository.getAll(pageable);
    }

    public Page<Product> getPaginatedProducts(Pageable pageable){
        return  repository.findAll(pageable);
    }

    public List<Product> getAll() {
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
