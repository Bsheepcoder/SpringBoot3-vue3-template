package com.example.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;

@Service
public interface AuthorizeService extends UserDetailsService {


    UserDetails loadUserByUsername(String username);
    String sendValidateEmail(String email, String sessionId);

    String validateAndRegister(String username,String password,String email,String code,String sessionId);
}
