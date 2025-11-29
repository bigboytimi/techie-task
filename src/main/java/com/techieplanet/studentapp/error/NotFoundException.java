package com.techieplanet.studentapp.error;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
