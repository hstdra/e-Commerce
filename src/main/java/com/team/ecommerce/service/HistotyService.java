package com.team.ecommerce.service;

import com.team.ecommerce.entity.History;
import com.team.ecommerce.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistotyService {

    @Autowired
    private HistoryRepository repository;

    public List<History> getAll(){
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void save(History history){
        repository.save(history);
    }
}
