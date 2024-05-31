package com.example.service;

import com.example.dto.user.UserRegistrationRequestDto;
import com.example.dto.user.UserResponseDto;
import com.example.exception.RegistrationException;
import com.example.mapper.UserMapper;
import com.example.model.User;
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
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException(
                    String.format("User with this email: %s already exists", requestDto.getEmail())
            );
        }
        User user = userMapper.toModel(requestDto);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }
}
