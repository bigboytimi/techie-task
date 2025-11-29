package com.techieplanet.studentapp.error;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

}
