package com.techieplanet.studentapp.service;

import com.techieplanet.studentapp.dtos.CreateStudentRequest;
import com.techieplanet.studentapp.dtos.ReportResponse;
import com.techieplanet.studentapp.dtos.StudentResponse;
import com.techieplanet.studentapp.error.BadRequestException;
import com.techieplanet.studentapp.error.NotFoundException;
import com.techieplanet.studentapp.model.Student;
import com.techieplanet.studentapp.repository.StudentRepository;
import com.techieplanet.studentapp.util.CalculatorUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

        String studentId = generateStudentId(req.getName());

        if (studentRepository.existsByStudentIdNo(studentId)) {
            throw new BadRequestException("Student with id " + studentId + " already exists");
        }

        Student student = new Student();
        student.setComputerScience(req.getComputerScience());
        student.setName(req.getName());
        student.setBiology(req.getBiology());
        student.setStudentIdNo(studentId);
        student.setMathematics(req.getMathematics());
        student.setChemistry(req.getChemistry());
        student.setPhysics(req.getPhysics());
        studentRepository.save(student);

        return toResponse(student);
    }

    @Transactional(readOnly = true)
    public ReportResponse report(String nameFilter, Double minMean, Double maxMean, Pageable pageable) {

        String normalizedName = (nameFilter == null || nameFilter.trim().isEmpty()) ? null : nameFilter.trim();

        Page<Student> studentPage;
        if (StringUtils.isEmpty(normalizedName)) {
            studentPage = studentRepository.findAll(pageable);
        } else {
            studentPage = studentRepository.findByNameContainingIgnoreCase(normalizedName, pageable);
        }

        List<StudentResponse> filtered = studentPage.getContent().stream()
                .map(this::toResponse)
                .filter(r ->
                        (minMean == null || r.mean >= minMean) &&
                                (maxMean == null || r.mean <= maxMean)
                )
                .toList();

        return new ReportResponse(
                studentPage.getTotalPages(),
                studentPage.getTotalElements(),
                studentPage.isFirst(),
                studentPage.isLast(),
                studentPage.getSize(),
                filtered
        );
    }


    public StudentResponse toResponse(Student s) {
        if (s == null) return null;

        Map<String, Integer> subjects = new LinkedHashMap<>();
        subjects.put("Biology", s.getBiology());
        subjects.put("Chemistry", s.getChemistry());
        subjects.put("Mathematics", s.getMathematics());
        subjects.put("Physics", s.getPhysics());
        subjects.put("Computer Science", s.getComputerScience());

        List<Integer> scores = new ArrayList<>(subjects.values());

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.studentIdNo = s.getStudentIdNo();
        studentResponse.name = s.getName();
        studentResponse.subjects = subjects;
        studentResponse.mean = CalculatorUtil.calculateMean(scores);
        studentResponse.median = CalculatorUtil.calculateMedian(scores);
        studentResponse.mode = CalculatorUtil.calculateMode(scores);

        return studentResponse;
    }


    public Student update(String studentIdNo, CreateStudentRequest request) {
        if (request == null) throw new BadRequestException("Request must not be null");

        Student student = studentRepository.findByStudentIdNo(studentIdNo)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + studentIdNo));

        student.setName(request.getName());
        student.setBiology(request.getBiology());
        student.setMathematics(request.getMathematics());
        student.setChemistry(request.getChemistry());
        student.setPhysics(request.getPhysics());
        student.setComputerScience(request.getComputerScience());
        return student;
    }

    public String generateStudentId(String name) {
        String cleanName = name.replaceAll("\\s+", "").toUpperCase();
        Random random = new Random();
        int randomSixDigits = 100000 + random.nextInt(900000);
        return cleanName + randomSixDigits;
    }

}
