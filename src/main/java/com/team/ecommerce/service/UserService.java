package com.team.ecommerce.service;

import com.team.ecommerce.entity.User;
import com.team.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;

    @Autowired
	public List<User> showAll() {
		return userRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}
	
	public List<User> searchByName(String name) {
		return userRepo.findByFullnameSorted(name);
	}
	
	public User getByEmail(String email) {
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

    public List<User> getAll() {
        return userRepo.findAll();
    }
}
