package com.team.ecommerce.repository;

import com.team.ecommerce.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
        
    List<User> findByOrderByFullnameAsc();
    
    @Query("from User where fullname=?1 order by id")
	List<User> findByFullnameSorted(String name);
}