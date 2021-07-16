package com.fnkaya.projectmanagement.security.service;

import com.fnkaya.projectmanagement.security.dto.AccountResponse;
import com.fnkaya.projectmanagement.security.dto.LoginRequest;
import com.fnkaya.projectmanagement.security.dto.LoginResponse;
import com.fnkaya.projectmanagement.security.entity.Account;
import com.fnkaya.projectmanagement.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    @Value("${security.jwt.secretKey}")
    private String key;
    private final Integer expirationDay = 7;

    private final AuthenticationProvider authenticationProvider;

    public LoginResponse login(LoginRequest loginRequest) throws AuthenticationException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authenticatedAccount = authenticationProvider.authenticate(authentication);
        String token = JwtUtil.generateToken(authenticatedAccount, key, expirationDay);
        return new LoginResponse(token, new AccountResponse((Account) authenticatedAccount.getPrincipal()));
    }
}
