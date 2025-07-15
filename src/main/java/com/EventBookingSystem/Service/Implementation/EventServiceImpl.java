package com.EventBookingSystem.Service.Implementation;

import com.EventBookingSystem.Dto.Requests.EventRequestDto;
import com.EventBookingSystem.Dto.Response.EventResponseDto;
import com.EventBookingSystem.Entity.Event;
import com.EventBookingSystem.Exception.ResourceNotFoundException;
import com.EventBookingSystem.Mapper.EventMapper;
import com.EventBookingSystem.Repository.EventRepository;
import com.EventBookingSystem.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    public EventResponseDto createEvent(EventRequestDto dto) {
        Event event = EventMapper.toEntity(dto);
        return EventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventResponseDto getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + id));
        return EventMapper.toDto(event);
    }

    @Override
    public List<EventResponseDto> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(EventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventResponseDto updateEvent(Long id, EventRequestDto dto) {
        Event event  = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + id));

        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setEventDateTime(dto.getEventDateTime());
        event.setCapacity(dto.getCapacity());
        event.setAvailableSeats(dto.getAvailableSeats());

        return EventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found with ID: " + id);
        }
        eventRepository.deleteById(id);
    }

    @Override
    public Page<EventResponseDto> getPaginatedEvents(Pageable pageable) {
        return eventRepository.findAll(pageable).map(EventMapper::toDto);
    }

    @Override
    public List<EventResponseDto> searchEventsByName(String name) {
        return eventRepository.findByNameContainingIgnoreCase(name)
                .stream().map(EventMapper::toDto)
                .collect(Collectors.toList());
    }
}
