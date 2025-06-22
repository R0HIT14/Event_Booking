package com.EventBookingSystem.Dto;

import com.EventBookingSystem.Enums.Role;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
