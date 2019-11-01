package com.team.ecommerce.repository;

import com.team.ecommerce.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}
