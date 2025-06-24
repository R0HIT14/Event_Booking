package com.EventBookingSystem.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String path;
    private int status;
    private String error;
}
