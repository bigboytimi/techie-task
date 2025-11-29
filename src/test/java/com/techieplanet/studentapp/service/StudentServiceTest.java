package com.techieplanet.studentapp.service;

import com.techieplanet.studentapp.dtos.CreateStudentRequest;
import com.techieplanet.studentapp.dtos.ReportResponse;
import com.techieplanet.studentapp.dtos.StudentResponse;
import com.techieplanet.studentapp.error.BadRequestException;
import com.techieplanet.studentapp.error.NotFoundException;
import com.techieplanet.studentapp.model.Student;
import com.techieplanet.studentapp.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * @author timiolowookere
 * @since 29-11-2025
 */
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createStudent_success() {
        CreateStudentRequest request = new CreateStudentRequest();
        request.setName("Timi Olowookere");
        request.setBiology(80);
        request.setChemistry(90);
        request.setMathematics(85);
        request.setPhysics(75);
        request.setComputerScience(95);

        when(studentRepository.existsByStudentIdNo(anyString())).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenAnswer(i -> i.getArguments()[0]);

        StudentResponse response = studentService.create(request);

        assertNotNull(response);
        assertEquals("Timi Olowookere", response.name);
        assertTrue(response.mean > 0);
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void createStudent_nullRequest_throwsBadRequest() {
        assertThrows(BadRequestException.class, () -> studentService.create(null));
    }

    @Test
    void createStudent_duplicateStudent_throwsBadRequest() {
        CreateStudentRequest request = new CreateStudentRequest();
        request.setName("Timi Olowookere");

        when(studentRepository.existsByStudentIdNo(anyString())).thenReturn(true);

        assertThrows(BadRequestException.class, () -> studentService.create(request));
    }

    @Test
    void updateStudent_success() {
        String studentId = "JOHN123456";
        CreateStudentRequest request = new CreateStudentRequest();
        request.setName("Timi Olowookere Updated");
        request.setBiology(70);
        request.setChemistry(80);
        request.setMathematics(90);
        request.setPhysics(60);
        request.setComputerScience(85);

        Student student = new Student();
        student.setStudentIdNo(studentId);
        when(studentRepository.findByStudentIdNo(studentId)).thenReturn(Optional.of(student));

        Student updated = studentService.update(studentId, request);

        assertEquals("Timi Olowookere Updated", updated.getName());
        assertEquals(70, updated.getBiology());
    }

    @Test
    void updateStudent_notFound_throwsNotFound() {
        when(studentRepository.findByStudentIdNo("UNKNOWN")).thenReturn(Optional.empty());
        CreateStudentRequest req = new CreateStudentRequest();
        assertThrows(NotFoundException.class, () -> studentService.update("UNKNOWN", req));
    }

    @Test
    void report_returnsFilteredReport() {
        Student s1 = new Student();
        s1.setName("Alice");
        s1.setBiology(80);
        s1.setChemistry(70);
        s1.setMathematics(90);
        s1.setPhysics(60);
        s1.setComputerScience(100);

        List<Student> students = List.of(s1);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> page = new PageImpl<>(students, pageable, students.size());

        when(studentRepository.findAll(pageable)).thenReturn(page);

        ReportResponse report = studentService.report(null, null, null, pageable);

        assertNotNull(report);
        assertEquals(1, report.getStudents().size());
        assertEquals("Alice", report.getStudents().get(0).name);
    }


}
