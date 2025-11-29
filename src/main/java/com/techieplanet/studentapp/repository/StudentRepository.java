package com.techieplanet.studentapp.repository;

import com.techieplanet.studentapp.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
public interface StudentRepository extends JpaRepository<Student, Long> {


    @Query("SELECT s FROM Student s WHERE (:name IS NULL OR lower(s.name) LIKE lower(concat('%',:name,'%'))) ")
    Page<Student> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    boolean existsByStudentIdNo(String studentIdNo);

    Optional<Student> findByStudentIdNo(String studentIdNo);
}
