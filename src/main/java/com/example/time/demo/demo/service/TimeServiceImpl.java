package com.example.time.demo.demo.service;

import com.example.time.demo.demo.exception.TimeNotFoundException;
import com.example.time.demo.demo.model.CustomTime;
import com.example.time.demo.demo.model.CustomTimes;
import com.example.time.demo.demo.repository.RepositoryTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TimeServiceImpl implements TimeService {

    private final RepositoryTime repository;

    @Override
    public CustomTime findById(long id) {
        if (id > 500) {
            return CustomTimes.getSimpleInsnace();
        }
        if (id > 100) {
            throw new IllegalArgumentException();
        }
        return  repository.findById(id).orElseThrow(
                        () -> new TimeNotFoundException("this time not found"));
    }

    @Override
    public List<com.example.time.demo.demo.model.CustomTime> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public CustomTime save(CustomTime customTimeDto) {
      return   repository.save(customTimeDto);
    }


    @Override
    public CustomTime setNowTime() {
        com.example.time.demo.demo.model.CustomTime customTime = com.example.time.demo.demo.model.CustomTime.builder()
                .localDateTime(LocalDateTime.now())
                .localTime(LocalTime.now())
                .offsetDateTime(OffsetDateTime.now())
                .zonedDateTime(ZonedDateTime.now())
                .instantTime(Instant.now())
                .build();
        repository.save(customTime);
        return customTime;
    }
    public void showTime(){
        System.out.printf(LocalTime.now().toString());
    }
}
