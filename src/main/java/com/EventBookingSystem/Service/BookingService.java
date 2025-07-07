package com.EventBookingSystem.Service;

import com.EventBookingSystem.Dto.Requests.BookingRequestDto;
import com.EventBookingSystem.Dto.Response.BookingResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookingService {

    BookingResponseDto createBooking(BookingRequestDto bookingRequestDto);

    BookingResponseDto getBookingById(Long id);

    List<BookingResponseDto> getAllBookings();

    void cancelBooking(Long id);

    List<BookingResponseDto> getBookingsByUserId(Long userId);

    List<BookingResponseDto> getBookingsByStatus(String status);

    void confirmBooking(Long id);

    void deleteBooking(Long id);

    Page<BookingResponseDto> getPaginatedBookings(Pageable pageable);
}
