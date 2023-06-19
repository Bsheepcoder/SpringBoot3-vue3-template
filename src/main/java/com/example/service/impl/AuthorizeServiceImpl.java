package com.example.service.impl;


import com.example.entity.Account;
import com.example.mapper.UserMapper;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Value("${spring.mail.username}")
    String from;


    //引入mapper
    @Resource
    UserMapper mapper;

    //引入redis
    @Resource
    StringRedisTemplate template;

    @Resource
    MailSender mailSender;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        if(username == null){
            throw new UsernameNotFoundException("user name can't be null");
        }
        Account account = mapper.findAccountByNameOrEmail(username);
        if(account == null){
            throw new UsernameNotFoundException("username or password is fault");
        }
        return User.withUsername(account.getUsername()).password(account.getPassword()).roles("USERS").build();
    }


    //邮箱验证实现
    @Override
    public boolean sendValidateEmail(String email,String sessionId){
        /*
         *  1.先生成应用的验证码
         *  2.把邮箱和对应的验证码直接放到Redis里面（过期时间3分钟，如果此时重新要求发邮件，那么只要剩余时间低于2分钟，就可以重新发一次，重复此流程）
         *  3.发送验证码到指定邮箱
         *  4.如果发送失败，把redis里面的刚插入的数据删除
         *  5.用户在注册时，再从Redis里面取出对应键值对，然后看验证码是否一致
         */
        String key = "email" + sessionId + ":" + email;
        if(Boolean.TRUE.equals(template.hasKey(key))){
            Long expire = Optional.ofNullable(template.getExpire(key,TimeUnit.SECONDS)).orElse(0L);
            if(expire > 120) return false;
        }

        Random random  = new Random();
        int code =random.nextInt(999999);
        //创建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("您的验证邮件");
        message.setText("您好，欢迎注册物流中台系统，验证码为：" + code);
        try{
            //发送邮件
            mailSender.send(message);

            template.opsForValue().set(key,String.valueOf(code),3,TimeUnit.MINUTES);
            return true;
        }  catch (MailException e){
            e.printStackTrace();
        }
        return false;
    }


}
