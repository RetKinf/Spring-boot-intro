package com.example.service;

import com.example.dto.user.UserRegistrationRequestDto;
import com.example.dto.user.UserResponseDto;
import com.example.exception.RegistrationException;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Email address already in use");
        }
        return userMapper.toUserResponse(userRepository.save(userMapper.toModel(requestDto)));
    }
}
