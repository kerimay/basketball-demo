package com.kerimay.user.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
