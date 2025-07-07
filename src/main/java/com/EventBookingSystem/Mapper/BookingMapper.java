package com.EventBookingSystem.Mapper;

import com.EventBookingSystem.Dto.Requests.BookingRequestDto;
import com.EventBookingSystem.Dto.Response.BookingResponseDto;
import com.EventBookingSystem.Entity.Booking;
import com.EventBookingSystem.Entity.User;
import com.EventBookingSystem.Enums.BookingStatus;

import java.time.LocalDateTime;

public class BookingMapper {

    public static Booking toEntity(BookingRequestDto dto, User user) {
        return Booking.builder()
                .bookingTime(LocalDateTime.now())
                .user(user)
                .seatsBooked(dto.getSeatsBooked())
                .status(BookingStatus.Confirmed) // or parse from dto if you're passing it
                .build();
    }

    public static BookingResponseDto toDto(Booking booking) {
        BookingResponseDto dto = new BookingResponseDto();
        dto.setId(booking.getId());
        dto.setBookingTime(booking.getBookingTime());
        dto.setUserName(booking.getUser().getName());
        dto.setSeatsBooked(booking.getSeatsBooked());
        dto.setStatus(booking.getStatus().name());
        return dto;
    }
}
