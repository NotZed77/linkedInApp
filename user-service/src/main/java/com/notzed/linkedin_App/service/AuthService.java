package com.notzed.linkedin_App.service;

import com.notzed.linkedin_App.dto.LoginRequestDto;
import com.notzed.linkedin_App.dto.SignupRequestDto;
import com.notzed.linkedin_App.dto.UserDto;

public interface AuthService {
    UserDto signUp(SignupRequestDto signupRequestDto);

    String login(LoginRequestDto loginRequestDto);
}
