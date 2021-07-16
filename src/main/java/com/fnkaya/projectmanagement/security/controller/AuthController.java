package com.fnkaya.projectmanagement.security.controller;

import com.fnkaya.projectmanagement.security.dto.LoginRequest;
import com.fnkaya.projectmanagement.security.dto.LoginResponse;
import com.fnkaya.projectmanagement.security.dto.RegisterRequest;
import com.fnkaya.projectmanagement.security.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AuthController {

    private final UserDetailsManager userDetailsManager;
    private final LoginService loginService;;

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public void signup(@RequestBody RegisterRequest registerRequest) {
        userDetailsManager.createUser(registerRequest.getDomainObject());
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }
}
