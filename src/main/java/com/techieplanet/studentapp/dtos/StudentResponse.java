package com.techieplanet.studentapp.dtos;

import lombok.Data;

import java.util.List;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
@Data
public class StudentResponse {
    public Long id;
    public String name;
    public List<Integer> scores;
    public double mean;
    public double median;
    public Integer mode;
}
