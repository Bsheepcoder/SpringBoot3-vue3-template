package com.example.controller;


import com.example.entity.RestBean;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {

    //正则表达式
    private final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    private final String USERNAME_REGEX = "^[\\u4e00-\\u9fa5a-zA-Z0-9_-]{4,16}$";

    @Resource
    AuthorizeService service;


    //邮箱验证
    @PostMapping("/valid-register-email")
    public RestBean<String> validateRegisterEmail(@Pattern (regexp = EMAIL_REGEX )@RequestParam("email") String email, HttpSession session){


        String s =  service.sendValidateEmail(email,session.getId(),false);
        if(s == null)
            return RestBean.success("邮件已发送,请注意查收");
        else
            return RestBean.failure(400,s);
    }

    @PostMapping("/valid-reset-email")
    public RestBean<String> validateResetEmail(@Pattern (regexp = EMAIL_REGEX )@RequestParam("email") String email, HttpSession session){
        String s =  service.sendValidateEmail(email,session.getId(),true);
        if(s == null)
            return RestBean.success("邮件已发送,请注意查收");
        else
            return RestBean.failure(400,s);
    }


    //用户注册
    @PostMapping("/register")
    public RestBean<String> registerUser(@Pattern(regexp = USERNAME_REGEX)@RequestParam("username") String username,
                                         @Length(min = 6,max = 16) @RequestParam("password") String password,
                                         @RequestParam("email") String email,
                                         @Length(min = 6,max = 6) @RequestParam("code") String code,
                                         HttpSession session){
        String s = service.validateAndRegister(username,password,email,code,session.getId());
        if(s == null){
            return RestBean.success("注册成功");
        }else {
            return RestBean.failure(400, s);
        }
    }

    @PostMapping("/start-rest")
    public RestBean<String> startRest(@RequestParam("email") String email,
                                      @Length(min = 6,max = 6) @RequestParam("code") String code,
                                      HttpSession session){
        String s = service.validateOnly(email,code,session.getId());
        if(s == null){
            session.setAttribute("rest-password",email);
            return RestBean.success();
        }else {
            return RestBean.failure(400,s);
        }
    }

    @PostMapping("/do-rest")
    public RestBean<String> restPassword(@Length(min = 6,max = 16) @RequestParam("password") String password,HttpSession session){
        String email = (String)session.getAttribute("rest-password");
        if(email == null){
            return RestBean.failure(401,"请先进行邮箱验证");
        }else if(service.resetPassword(password,email)){
            session.removeAttribute("reset-password");
            return RestBean.success("密码重置成功");
        }else{
            return RestBean.failure(500,"内部错误，请联系管理员");
        }
    }

}
