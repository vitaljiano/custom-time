package com.example.time.demo.demo.controller;

import com.example.time.demo.demo.exception.TimeNotFoundException;
import com.example.time.demo.demo.model.CustomTime;
import com.example.time.demo.demo.service.TimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/time")
@Tag(name = "CustomTime", description = "The Custom Time Api")
public class Controller {

    private final TimeService service;

    @Operation(summary = "Find time by id", description = "Find time by id")
    @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomTime.class)))
    @ApiResponse(responseCode = "500", description = "Server error")
    @ApiResponse(responseCode = "404",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TimeNotFoundException.class))))
    @GetMapping("/{id}")
    public CustomTime findById(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @Operation(summary = "Get all time", description = "Get all time")
    @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomTime.class)))
    @ApiResponse(responseCode = "500", description = "Server error")
    @GetMapping("/")
    public List<CustomTime> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Save custom time", description = "Save custom time")
    @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomTime.class)))
    @ApiResponse(responseCode = "500", description = "Server error")
    @PostMapping("/")
    public ResponseEntity<Void> createTime(@RequestBody CustomTime customTime) {
        service.save(customTime);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Show time now", description = "Show time now")
    @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomTime.class)))
    @ApiResponse(responseCode = "500", description = "Server error")
    @GetMapping("/show-time")
    public String showTimeNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Operation(summary = "Save time now", description = "Save time now")
    @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomTime.class)))
    @ApiResponse(responseCode = "500", description = "Server error")
    @GetMapping("/set-time-now")
    public CustomTime setTimNow() {
        return service.setNowTime();
    }
}