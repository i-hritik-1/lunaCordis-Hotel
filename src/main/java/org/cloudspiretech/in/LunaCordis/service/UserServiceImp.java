package org.cloudspiretech.in.LunaCordis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cloudspiretech.in.LunaCordis.dto.UserDto;
import org.cloudspiretech.in.LunaCordis.entity.User;
import org.cloudspiretech.in.LunaCordis.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Creating a new user with email: {}, {}", userDto.getEmail(), userDto.getName());
        User user = modelMapper.map(userDto, User.class);
        user = userRepository.save(user);
        log.info("User has been created with id {}", user.getId());
        return modelMapper.map(user, UserDto.class);
    }
}
