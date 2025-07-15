package com.EventBookingSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GeneratorType;

import java.time.LocalDateTime;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String description;

    private String location;

    private LocalDateTime eventDateTime;

    private Integer capacity;

    private Integer availableSeats;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
