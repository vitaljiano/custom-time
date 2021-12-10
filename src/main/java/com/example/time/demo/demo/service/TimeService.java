package com.example.time.demo.demo.service;

import com.example.time.demo.demo.model.CustomTime;

import java.util.List;

public interface TimeService {
    CustomTime findById(long id);

    List<CustomTime> getAll();

    CustomTime save(CustomTime customTimeDto);

    CustomTime setNowTime();
}
