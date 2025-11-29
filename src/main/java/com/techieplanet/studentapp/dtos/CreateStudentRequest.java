package com.techieplanet.studentapp.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
@Data
public class CreateStudentRequest {
    @NotBlank
    public String name;
    @NotNull
    @Min(0) @Max(100)
    public Integer mathematics;
    @NotNull @Min(0) @Max(100)
    public Integer computerScience;
    @NotNull @Min(0) @Max(100)
    public Integer biology;
    @NotNull @Min(0) @Max(100)
    public Integer chemistry;
    @NotNull @Min(0) @Max(100)
    public Integer physics;
}
