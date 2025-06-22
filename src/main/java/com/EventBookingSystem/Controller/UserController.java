package com.EventBookingSystem.Controller;

import com.EventBookingSystem.Dto.UserRequestDto;
import com.EventBookingSystem.Dto.UserResponseDto;
import com.EventBookingSystem.Entity.User;
import com.EventBookingSystem.Mapper.UserMapper;
import com.EventBookingSystem.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto){
        User user = userService.createUser(UserMapper.toEntity(userRequestDto));
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
//        List<UserResponseDto> list = userService.getAllUsers().stream()
//                .map(user -> UserMapper.toDto(user)).collect(Collectors.toList());

        List<UserResponseDto> list = userService.getAllUsers()
                .stream()
                .map(UserMapper::toDto)
                .toList();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable long id, @Valid @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(UserMapper.toDto(userService.updateUser(id, UserMapper.toEntity(userRequestDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User with id : " +id+" successfully deleted");
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<UserResponseDto>> getAllUsersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userService.getAllUsersPaginated(pageable);

        Page<UserResponseDto> responsePage = userPage.map(UserMapper::toDto);

        return ResponseEntity.ok(responsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable long id){
        return ResponseEntity.ok(UserMapper.toDto(userService.getUserById(id)));
    }
}
