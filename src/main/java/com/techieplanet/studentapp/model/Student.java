package com.techieplanet.studentapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String name;


    @Min(0) @Max(100)
    @Column(nullable = false, length = 100)
    private Integer mathematics;


    @Min(0) @Max(100)
    @Column(name = "computer_science", nullable = false, length = 100)
    private Integer computerScience;


    @Min(0) @Max(100)
    private Integer biology;


    @Min(0) @Max(100)
    private Integer chemistry;


    @Min(0) @Max(100)
    private Integer physics;
}
