package com.EventBookingSystem.Controller;

import com.EventBookingSystem.Dto.Requests.BookingRequestDto;
import com.EventBookingSystem.Dto.Response.BookingResponseDto;
import com.EventBookingSystem.Service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Create a booking", description = "Creates a booking for a user")
    @ApiResponse(responseCode = "201", description = "Booking created successfully")
    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@Valid @RequestBody BookingRequestDto dto) {
        BookingResponseDto response = bookingService.createBooking(dto);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Get all bookings", description = "Fetches all bookings")
    @ApiResponse(responseCode = "200", description = "Bookings fetched successfully")
    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @Operation(summary = "Get booking by ID", description = "Fetches a single booking by its ID")
    @ApiResponse(responseCode = "200", description = "Booking found")
    @ApiResponse(responseCode = "404", description = "Booking not found")
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @Operation(summary = "Cancel booking", description = "Cancels a booking")
    @ApiResponse(responseCode = "200", description = "Booking cancelled successfully")
    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled with ID: " + id);
    }

    // 🔽 Additional Methods

    @Operation(summary = "Get bookings by user", description = "Fetches all bookings for a specific user")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDto>> getBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }

    @Operation(summary = "Get bookings by status", description = "Fetches bookings filtered by booking status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<BookingResponseDto>> getBookingsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(bookingService.getBookingsByStatus(status));
    }

    @Operation(summary = "Confirm booking", description = "Sets a pending booking to confirmed")
    @PutMapping("/{id}/confirm")
    public ResponseEntity<String> confirmBooking(@PathVariable Long id) {
        bookingService.confirmBooking(id);
        return ResponseEntity.ok("Booking confirmed with ID: " + id);
    }

    @Operation(summary = "Delete a booking", description = "Deletes a booking permanently")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted with ID: " + id);
    }

    @Operation(summary = "Paginated bookings", description = "Returns bookings with pagination support")
    @GetMapping("/paginated")
    public ResponseEntity<Page<BookingResponseDto>> getPaginatedBookings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(bookingService.getPaginatedBookings(PageRequest.of(page, size)));
    }
}
