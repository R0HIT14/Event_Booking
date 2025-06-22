package com.EventBookingSystem.Mapper;

import com.EventBookingSystem.Dto.UserRequestDto;
import com.EventBookingSystem.Dto.UserResponseDto;
import com.EventBookingSystem.Entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDto dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();
    }

    public static UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}
