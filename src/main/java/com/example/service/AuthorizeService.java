package com.example.service;


import com.example.entity.Account;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService implements UserDetailsService {


    //引入mapper
    @Resource
    UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        if(username == null){
            throw new UsernameNotFoundException("user name can't be null");
        }
        Account account = mapper.findAccountByNameOrEmail(username);
        if(account == null){
            throw new UsernameNotFoundException("username or password is fault");
        }
        return User.withUsername(account.getUsername()).password(account.getPassword()).roles("user").build();
    }
}
