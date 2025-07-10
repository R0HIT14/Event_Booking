package com.EventBookingSystem.Service.Implementation;

import com.EventBookingSystem.Dto.Requests.BookingRequestDto;
import com.EventBookingSystem.Dto.Response.BookingResponseDto;
import com.EventBookingSystem.Entity.Booking;
import com.EventBookingSystem.Entity.User;
import com.EventBookingSystem.Enums.BookingStatus;
import com.EventBookingSystem.Exception.ResourceNotFoundException;
import com.EventBookingSystem.Mapper.BookingMapper;
import com.EventBookingSystem.Repository.BookingRepository;
import com.EventBookingSystem.Repository.UserRepository;
import com.EventBookingSystem.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BookingResponseDto createBooking(BookingRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.getUserId()));

        Booking booking = BookingMapper.toEntity(dto, user);
        booking.setStatus(BookingStatus.Confirmed); // or use dto.getStatus()

        Booking saved = bookingRepository.save(booking);
        return BookingMapper.toDto(saved);
    }

    @Override
    public BookingResponseDto getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + id));
        return BookingMapper.toDto(booking);
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(BookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + id));
        booking.setStatus(BookingStatus.Cancelled);
        bookingRepository.save(booking);
    }

    @Override
    public List<BookingResponseDto> getBookingsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        return bookingRepository.findByUser(user)
                .stream()
                .map(BookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponseDto> getBookingsByStatus(String status) {
        BookingStatus bookingStatus;
        try {
            bookingStatus = BookingStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Invalid booking status: " + status);
        }

        return bookingRepository.findByStatus(bookingStatus)
                .stream()
                .map(BookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void confirmBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + id));
        booking.setStatus(BookingStatus.Confirmed);
        bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Booking not found with ID: " + id);
        }
        bookingRepository.deleteById(id);
    }

    @Override
    public Page<BookingResponseDto> getPaginatedBookings(Pageable pageable) {
        return bookingRepository.findAll(pageable).map(BookingMapper::toDto);
    }
}
