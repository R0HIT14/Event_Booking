package com.EventBookingSystem.Dto.Requests;

import com.EventBookingSystem.Enums.BookingStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRequestDto {
    private Long userId;
    private Long eventId;
    private int seatsBooked;
    private BookingStatus bookingStatus;
}
