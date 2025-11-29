package com.techieplanet.studentapp.dtos;

import lombok.Data;

import java.util.Map;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
@Data
public class StudentResponse {
    public String name;
    public String studentIdNo;
    public Map<String, Integer> subjects;
    public double mean;
    public double median;
    public Integer mode;
}
