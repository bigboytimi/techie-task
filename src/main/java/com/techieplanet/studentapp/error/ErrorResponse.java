package com.techieplanet.studentapp.error;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private int code;
    private String message;
    private String error;
}
