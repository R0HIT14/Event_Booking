package com.EventBookingSystem.Service;

import com.EventBookingSystem.Dto.Requests.EventRequestDto;
import com.EventBookingSystem.Dto.Response.EventResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {

    EventResponseDto createEvent(EventRequestDto event);

    EventResponseDto getEventById(Long id);

    List<EventResponseDto> getAllEvents();

    EventResponseDto updateEvent(Long id, EventRequestDto event);

    void deleteEvent(Long id);

    Page<EventResponseDto> getPaginatedEvents(Pageable pageable);

    List<EventResponseDto> searchEventsByName(String name);
}
