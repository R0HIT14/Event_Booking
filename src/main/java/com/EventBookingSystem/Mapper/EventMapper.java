package com.EventBookingSystem.Mapper;

import com.EventBookingSystem.Dto.Requests.EventRequestDto;
import com.EventBookingSystem.Dto.Response.EventResponseDto;
import com.EventBookingSystem.Entity.Event;


public class EventMapper {

    public static Event toEntity(EventRequestDto dto) {
        return Event.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .eventDateTime(dto.getEventDateTime())
                .capacity(dto.getCapacity())
                .availableSeats(dto.getAvailableSeats() != null ? dto.getAvailableSeats() : dto.getCapacity()) // fallback
                .build();
    }

    public static EventResponseDto toDto(Event event) {
        EventResponseDto dto = new EventResponseDto();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setDescription(event.getDescription());
        dto.setLocation(event.getLocation());
        dto.setEventDateTime(event.getEventDateTime());
        dto.setCapacity(event.getCapacity());
        dto.setAvailableSeats(event.getAvailableSeats());
        dto.setCreatedAt(event.getCreatedAt());
        dto.setUpdatedAt(event.getUpdatedAt());
        return dto;
    }
}

