//package com.example.controller;
//
//
//import com.example.entity.RestBean;
//import com.example.service.AuthorizeService;
//import jakarta.annotation.Resource;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.constraints.Pattern;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthorizeController {
//
//    private final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
//
//    @Resource
//    AuthorizeService service;
//
//    @PostMapping("/valid-email")
//    public RestBean<String> validateEmail(@Pattern(regexp = EMAIL_REGEX )@RequestParam("email") String email){
//        if(service.sendValidateEmail(email))
//            return RestBean.success("邮件已发送,请注意查收");
//        else
//            return RestBean.failure(400,"邮箱发送失败，请联系管理员");
//    }
//}
