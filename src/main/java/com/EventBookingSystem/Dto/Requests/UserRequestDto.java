package com.EventBookingSystem.Dto.Requests;

import com.EventBookingSystem.Enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Enter valid email")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private Role role;
}
