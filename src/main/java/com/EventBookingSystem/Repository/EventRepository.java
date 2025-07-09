package com.EventBookingSystem.Repository;

import com.EventBookingSystem.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
