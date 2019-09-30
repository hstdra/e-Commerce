package com.team.ecommerce.service;

import com.team.ecommerce.entity.Category;
import com.team.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<Category> listAll() {
        return repository.findAll();
    }

    public Category get(int id) {
        return repository.getOne(id);
    }

    public void save(Category product) {
        repository.save(product);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
