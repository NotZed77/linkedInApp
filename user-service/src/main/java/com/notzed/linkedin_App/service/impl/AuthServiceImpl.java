package com.notzed.linkedin_App.service.impl;

import com.notzed.linkedin_App.dto.LoginRequestDto;
import com.notzed.linkedin_App.dto.SignupRequestDto;
import com.notzed.linkedin_App.dto.UserDto;
import com.notzed.linkedin_App.entity.User;
import com.notzed.linkedin_App.exception.BadRequestException;
import com.notzed.linkedin_App.exception.ResourceNotFoundException;
import com.notzed.linkedin_App.repository.UserRepository;
import com.notzed.linkedin_App.service.AuthService;
import com.notzed.linkedin_App.service.JwtService;
import com.notzed.linkedin_App.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public UserDto signUp(SignupRequestDto signupRequestDto) {

        boolean exists = userRepository.existsByEmail(signupRequestDto.getEmail());
            if(exists) {
                throw new BadRequestException("User already exists, cannot signup again.");
            }

        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(PasswordUtil.hashPassword(signupRequestDto.getPassword()));

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public String login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: "+ loginRequestDto.getEmail()));

        boolean isPasswordOfMatch = PasswordUtil
                .checkPassword(loginRequestDto.getPassword(), user.getPassword());

        if(!isPasswordOfMatch){
            throw new BadRequestException("Incorrect Password");
        }

        return jwtService.generateAccessToken(user);
    }
}
