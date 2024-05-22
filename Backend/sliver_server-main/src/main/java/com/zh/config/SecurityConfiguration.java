package com.zh.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.filter.JsonUsernamePasswordFilter;
import com.zh.filter.JwtAuthenticationLoginFilter;
import com.zh.handler.CustomAuthenticationEntryPoint;
import com.zh.handler.MyAuthenticationFailureHandler;
import com.zh.handler.MyAuthenticationSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private JwtAuthenticationLoginFilter jwtAuthenticationLoginFilter;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(7);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(authenticationProvider());
    }


    /**
     * @return 向Spring框架中注入PasswordEncoder
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   CustomAuthenticationEntryPoint customAuthenticationEntryPoint
                                                   )
                                                    throws Exception {
        //在Security框架中,登录页面和登录请求地址默认不校验权限,不校验认证
        Customizer<FormLoginConfigurer<HttpSecurity>> customizer = new Customizer<FormLoginConfigurer<HttpSecurity>>() {
            @Override
            public void customize(FormLoginConfigurer<HttpSecurity> configurer) {
                configurer
                        .loginProcessingUrl("/userLogin") //登录请求地址,还是使用spring的请求处理方法,不会被controller覆盖
                        .successHandler(authenticationSuccessHandler)
                        .failureHandler(authenticationFailureHandler)
                        ; //设置认证失败后的处理代码逻辑 userLogin地址登录失败的操作
//                        .usernameParameter("username")  //设置请求参数名称,用户名
//                        .passwordParameter("password")  //设置请求参数名称,密码
//                        .defaultSuccessUrl("")  //设置默认的认证成功后跳转地址,仅在直接访问登录地址时生效
//                        .successForwardUrl("")  //设置认证成功后的转发地址,全局生效
            }
        };
        //设置认证配置
        http.formLogin(customizer);


        //设置JWT验证过滤器在UsernamePasswordAuthenticationFilter之前
        http.addFilterBefore(jwtAuthenticationLoginFilter, UsernamePasswordAuthenticationFilter.class);



        //授权配置
        http.authorizeRequests()
                .requestMatchers("user/login","/user/attemptRegister", "/user/CodeRegister", "/user/loginJson","/userLogin").permitAll()
                .anyRequest().authenticated();
        //
        http.exceptionHandling((exception) -> exception.
                authenticationEntryPoint(customAuthenticationEntryPoint));

        http.csrf().disable();

        return http.build();
    }

}
