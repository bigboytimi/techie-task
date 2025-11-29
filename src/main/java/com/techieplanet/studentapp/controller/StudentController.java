package com.techieplanet.studentapp.controller;

import com.techieplanet.studentapp.common.Constants;
import com.techieplanet.studentapp.dtos.CreateStudentRequest;
import com.techieplanet.studentapp.dtos.StudentResponse;
import com.techieplanet.studentapp.model.Student;
import com.techieplanet.studentapp.service.StudentService;
import lombok.RequiredArgsConstructor;
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
public class StudentController {

    private final StudentService service;

    @PostMapping
    public ResponseEntity<StudentResponse> create(@RequestBody CreateStudentRequest request) {
        StudentResponse created = service.create(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(
            @PathVariable Long id,
            @RequestBody CreateStudentRequest request) {

        Student updated = service.update(id, request);
        return ResponseEntity.ok(service.toResponse(updated));
    }

    @GetMapping
    public ResponseEntity<Page<StudentResponse>> report(
            @RequestParam String name,
            @RequestParam(required = false) Double minMean,
            @RequestParam(required = false) Double maxMean,
            @RequestParam(required = false, defaultValue = "0") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<StudentResponse> result = service.report(name, minMean, maxMean, pageable);
        return ResponseEntity.ok(result);
    }
}
