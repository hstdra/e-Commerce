package com.team.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.ecommerce.entity.User;
import com.team.ecommerce.repository.UserRepository;

@Service
public class CustomerService {
	@Autowired
	UserRepository userRepo;
	
	public List<User> showAll() {
		return userRepo.findAll();
	}
	
	public List<User> searchByName(String name) {
		return userRepo.findByFullnameSorted(name);
	}
	
	public User searchByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public List<User> sort(List<User> list) {
		return userRepo.findByOrderByFullnameAsc();
	}
	
	public void delete(int id) {
        userRepo.deleteById(id);
    }
	
	public User get(int id) {
        return userRepo.getOne(id);
    }
	
	public void save(User u) {
		userRepo.save(u);
	}
}
