package com.example.time.demo.demo.service;

import com.example.time.demo.demo.exception.FieldIsEmpty;
import com.example.time.demo.demo.exception.TimeNotFoundException;
import com.example.time.demo.demo.model.CustomTime;
import com.example.time.demo.demo.repository.RepositoryTime;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.*;
import java.util.List;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    private final RepositoryTime repository;

    @Override
    public CustomTime getById(long id) {
        return repository.findById(id).orElseThrow(() -> new TimeNotFoundException("this time not found"));
    }

    @Override
    public List<CustomTime> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void save(CustomTime customTime) {
        if (isNotEmpty(customTime)) {
            repository.save(customTime);
        }
    }

    public boolean isNotEmpty(Object o) {
        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if(!field.getName().equals("id")) {
                    if (field.get(o) == null) {
                        throw new FieldIsEmpty("Field " + field + " is empty");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public CustomTime setNowTime() {
        CustomTime customTime = CustomTime.builder()
                .localDateTime(LocalDateTime.now())
                .localTime(LocalTime.now())
                .offsetDateTime(OffsetDateTime.now())
                .zonedDateTime(ZonedDateTime.now())
                .instantTime(Instant.now())
                .build();
        repository.save(customTime);
        return customTime;
    }
}
