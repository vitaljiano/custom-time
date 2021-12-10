package com.example.time.demo.demo.repository;

import com.example.time.demo.demo.model.CustomTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface RepositoryTime extends JpaRepository<CustomTime, Long> {
    CustomTime findByLocalTime(LocalTime localTime);
}
