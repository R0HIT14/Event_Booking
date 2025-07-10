package com.EventBookingSystem.Repository;

import com.EventBookingSystem.Entity.Booking;
import com.EventBookingSystem.Entity.User;
import com.EventBookingSystem.Enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);

    List<Booking> findByStatus(BookingStatus status);

}
