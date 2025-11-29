package com.techieplanet.studentapp.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.techieplanet.studentapp.common.Constants;
import com.techieplanet.studentapp.dtos.CreateStudentRequest;
import com.techieplanet.studentapp.dtos.ReportResponse;
import com.techieplanet.studentapp.dtos.StudentResponse;
import com.techieplanet.studentapp.model.Student;
import com.techieplanet.studentapp.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */

@RestController
@RequestMapping(value = Constants.BASE_API)
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService service;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @PostMapping
    public ResponseEntity<StudentResponse> create(@RequestBody @Valid CreateStudentRequest request) {
        log.info("Create Student Request: {}", gson.toJson(request));
        StudentResponse created = service.create(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResponse> update(
            @PathVariable String studentId,
            @RequestBody @Valid  CreateStudentRequest request) {

        log.info("Update request received. Request: {}", gson.toJson(request));
        Student updated = service.update(studentId, request);
        return ResponseEntity.ok(service.toResponse(updated));
    }

    @GetMapping
    public ResponseEntity<ReportResponse> report(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minMean,
            @RequestParam(required = false) Double maxMean,
            @RequestParam(required = false, defaultValue = "0") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {

        log.info("Report request received. Name: {} | Minimum Mean: {} | Maximum Mean {}", name, minMean, maxMean);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        ReportResponse result = service.report(name, minMean, maxMean, pageable);
        return ResponseEntity.ok(result);
    }
}
