package org.cloudspiretech.in.LunaCordis.controller;

import lombok.AllArgsConstructor;
import org.cloudspiretech.in.LunaCordis.dto.UserDto;
import org.cloudspiretech.in.LunaCordis.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto user = userService.createUser(userDto);
        return ResponseEntity.ok(user);
    }
}
