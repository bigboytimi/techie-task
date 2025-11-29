package com.techieplanet.studentapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private int totalPages;
    private long totalElements;
    private boolean first;
    private boolean last;
    private int size;
    private List<StudentResponse> students;
}
