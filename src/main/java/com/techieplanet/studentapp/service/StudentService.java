package com.techieplanet.studentapp.service;

import com.techieplanet.studentapp.dtos.CreateStudentRequest;
import com.techieplanet.studentapp.dtos.StudentResponse;
import com.techieplanet.studentapp.error.BadRequestException;
import com.techieplanet.studentapp.error.NotFoundException;
import com.techieplanet.studentapp.model.Student;
import com.techieplanet.studentapp.repository.StudentRepository;
import com.techieplanet.studentapp.util.CalculatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentResponse create(CreateStudentRequest req) {
        if (req == null) throw new BadRequestException("Request must not be null");

        Student student = new Student();
        student.setComputerScience(req.getComputerScience());
        student.setName(req.getName());
        student.setBiology(req.getBiology());

        student.setMathematics(req.getMathematics());
        student.setChemistry(req.getChemistry());
        student.setPhysics(req.getPhysics());
        studentRepository.save(student);

        return toResponse(student);
    }

    @Transactional(readOnly = true)
    public Page<StudentResponse> report(String nameFilter, Double minMean, Double maxMean, Pageable pageable) {
        String normalizedName = (nameFilter == null || nameFilter.trim().isEmpty()) ? null : nameFilter.trim();

        Page<Student> studentPage = studentRepository.findByNameContainingIgnoreCase(normalizedName, pageable);
        List<Student> students = studentPage.getContent();

        List<StudentResponse> allResponses = students.stream()
                .map(this::toResponse)
                .toList();

        List<StudentResponse> filtered = allResponses.stream()
                .filter(r -> {
                    boolean okMin = (minMean == null) || (r.mean >= minMean);
                    boolean okMax = (maxMean == null) || (r.mean <= maxMean);
                    return okMin && okMax;
                })
                .collect(Collectors.toList());

        long total = filtered.size();


        if (pageable == null || pageable.isUnpaged()) {
            return new PageImpl<>(filtered, pageable == null ? Pageable.unpaged() : pageable, total);
        }

        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int fromIndex = pageNumber * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, filtered.size());

        List<StudentResponse> pageContent;
        if (fromIndex >= filtered.size()) {
            pageContent = Collections.emptyList();
        } else {
            pageContent = filtered.subList(fromIndex, toIndex);
        }

        return new PageImpl<>(pageContent, pageable, total);
    }

    @Transactional(readOnly = true)
    public StudentResponse getById(Long id) {
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));
        return toResponse(s);
    }

    public StudentResponse toResponse(Student s) {
        if (s == null) return null;
        List<Integer> scores = Arrays.asList(
                s.getBiology(),
                s.getChemistry(),
                s.getMathematics(),
                s.getPhysics(),
                s.getComputerScience()
        );

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.id = s.getId();
        studentResponse.name = s.getName();
        studentResponse.scores = scores;
        studentResponse.mean = CalculatorUtil.calculateMean(scores);
        studentResponse.median = CalculatorUtil.calculateMedian(scores);
        studentResponse.mode = CalculatorUtil.calculateMode(scores);
        return studentResponse;
    }

    public Student update(Long id, CreateStudentRequest request) {
        if (request == null) throw new BadRequestException("Request must not be null");

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));

        student.setName(request.getName());
        student.setBiology(request.getBiology());
        student.setMathematics(request.getMathematics());
        student.setChemistry(request.getChemistry());
        student.setPhysics(request.getPhysics());
        student.setComputerScience(request.getComputerScience());
        return student;
    }
}
