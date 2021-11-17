package com.example.time.demo.demo.service;

import com.example.time.demo.demo.model.CustomTime;

import java.util.List;

public interface Service {
     CustomTime getById(long id);

     List<CustomTime> getAll();

     void save(CustomTime customTime);

     CustomTime setNowTime();
}
