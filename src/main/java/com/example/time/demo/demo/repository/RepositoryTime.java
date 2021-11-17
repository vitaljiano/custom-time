package com.example.time.demo.demo.repository;

import com.example.time.demo.demo.model.CustomTime;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface RepositoryTime extends JpaRepository<CustomTime, Long> {
}
