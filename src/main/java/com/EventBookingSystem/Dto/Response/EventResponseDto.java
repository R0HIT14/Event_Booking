package com.EventBookingSystem.Dto.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventResponseDto {
    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDateTime eventDateTime;
    private Integer capacity;
    private Integer availableSeats;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
