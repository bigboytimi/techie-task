package com.techieplanet.studentapp.error;

import lombok.Getter;
import lombok.Setter;

/**
 * @author timiolowookere
 * @since 29-11-2025
 */
@Getter
public enum ResponseCode {

    REQUEST_FAILED(99, "Failed to complete request"),
    SUCCESS_REQUEST(200, "Request processed successfully");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
