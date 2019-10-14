package com.team.ecommerce.service;

import com.team.ecommerce.entity.Field;
import com.team.ecommerce.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {
    @Autowired
    private FieldRepository repository;

    public List<Field> listAll() {
        return repository.findAll();
    }

    public Field getById(int id) {
        return repository.getOne(id);
    }

    public void save(Field field) {
        repository.save(field);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

}
