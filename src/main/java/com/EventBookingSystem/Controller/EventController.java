package com.EventBookingSystem.Controller;

import com.EventBookingSystem.Dto.Requests.EventRequestDto;
import com.EventBookingSystem.Dto.Response.EventResponseDto;
import com.EventBookingSystem.Service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponseDto> createEvent(@RequestBody @Valid EventRequestDto event) {
        EventResponseDto response = eventService.createEvent(event);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDto> updateEvent(@PathVariable Long id, @RequestBody @Valid EventRequestDto request) {
        return ResponseEntity.ok(eventService.updateEvent(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully with id: " + id);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<EventResponseDto>> getPaginatedEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(eventService.getPaginatedEvents(PageRequest.of(page, size)));
    }

    @GetMapping("/search")
    public ResponseEntity<List<EventResponseDto>> searchEventByName(@RequestParam String name) {
        return ResponseEntity.ok(eventService.searchEventsByName(name));
    }
}
