package com.team.ecommerce.service;

import com.team.ecommerce.entity.FieldDetail;
import com.team.ecommerce.repository.FieldDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldDetailService {
    @Autowired
    private FieldDetailRepository repository;

    public List<FieldDetail> listAll() {
        return repository.findAll();
    }

    public FieldDetail getById(int id) {
        return repository.getOne(id);
    }

    public void save(FieldDetail fieldDetail) {
        repository.save(fieldDetail);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

}
