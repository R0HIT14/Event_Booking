package com.EventBookingSystem.Dto.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingResponseDto {
    private Long id;
    private LocalDateTime bookingTime;
    private String userName;
    private int seatsBooked;
    private String status;

    // Optional placeholder if needed later
    // private String eventName;
}
