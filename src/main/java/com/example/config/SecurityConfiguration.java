//package com.example.config;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.example.entity.RestBean;
//import com.example.service.AuthorizeService;
//import jakarta.annotation.Resource;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Resource
//    AuthorizeService authorizeservice;
//
//    @Resource
//    DataSource dataSource;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http,PersistentTokenRepository repository) throws Exception {
//        return http
//                .authorizeHttpRequests()
//                .requestMatchers("/api/auth/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/api/auth/login")
//                .successHandler(this::onAuthenticationSuccess)
//                .failureHandler(this::onAuthenticationFailure)
//                .and()
//                .logout()
//                .logoutUrl("/api/auth/logout")
//                .logoutSuccessHandler(this::onAuthenticationSuccess)
//                .and()
//                .rememberMe()
//                .rememberMeParameter("remember")
//                .tokenRepository(repository)
//                .tokenValiditySeconds(3600 * 24 * 7)
//                .and()
//                .csrf()
//                .disable()
//                .cors()
//                .configurationSource(this.corsConfigurationSource()) //解决跨域问题
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(this::onAuthenticationFailure)
//                .and()
//                .build();
//    }
//
//    //持久化token仓库
//    @Bean
//    protected PersistentTokenRepository tokenRepository(){
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        //设定数据源
//        jdbcTokenRepository.setDataSource(dataSource);
//        //一开始创建表,后面改成false
//        jdbcTokenRepository.setCreateTableOnStartup(false);
//        return jdbcTokenRepository;
//    }
//
//    //解决跨域问题
//    private CorsConfigurationSource corsConfigurationSource(){
//        CorsConfiguration cors = new CorsConfiguration();
//        //只有测试的时候才能写成*号，生产环境要好好配置
//        cors.addAllowedOriginPattern("*");
//        cors.setAllowCredentials(true);
//        cors.addAllowedHeader("*");
//        cors.addAllowedMethod("*");
//        cors.addExposedHeader("*");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**",cors);
//        return source;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity security) throws Exception {
//        return security
//                .getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(authorizeservice)
//                .and()
//                .build();
//    }
//
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
//        response.setCharacterEncoding("utf-8");
//        if(request.getRequestURI().endsWith("/login"))
//            response.getWriter().write(JSONObject.toJSONString(RestBean.success("登录成功")));
//        else if(request.getRequestURI().endsWith("/logout"))
//            response.getWriter().write(JSONObject.toJSONString(RestBean.success("成功退出登录")));
//    }
//
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException{
//        response.setCharacterEncoding("utf-8");
//        response.getWriter().write(JSONObject.toJSONString(RestBean.failure(401,exception.getMessage())));
//    }
//}
