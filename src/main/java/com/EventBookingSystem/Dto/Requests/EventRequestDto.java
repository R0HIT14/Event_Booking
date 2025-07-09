package com.EventBookingSystem.Dto.Requests;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EventRequestDto {
    private String name;
    private String description;
    private String location;
    private LocalDateTime eventDateTime;
    private Integer capacity;
    private Integer availableSeats;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
