package com.example.time.demo.demo.controller;

import com.example.time.demo.demo.model.CustomTime;
import com.example.time.demo.demo.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class Controller {

    private final Service service;

    @GetMapping("/{id}")
    public CustomTime findById(@PathVariable("id") long id) {
        return service.getById(id);
    }

    @GetMapping("/")
    public List<CustomTime> getAll() {
        return service.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<Void> createTime(@RequestBody CustomTime customTime) {
        service.save(customTime);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/show-time")
    public String showTimeNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @GetMapping("/set-time-now")
    public CustomTime setTimNow() {
        return service.setNowTime();
    }
}