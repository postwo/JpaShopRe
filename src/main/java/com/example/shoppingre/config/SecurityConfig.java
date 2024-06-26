package com.example.shoppingre.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {



        //csrf disable (이게 있으면 프론트단에 <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"> 이걸 안먹여도 된다)
        http
                .csrf((auth) -> auth.disable());


        // 각 페이지에 대한 접근 권한 설정
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/css/**", "/js/**", "/img/**","/error").permitAll() //"/error"999에러 해결
                .requestMatchers("/", "/member/**", "/item/**", "/images/**","/register/**","/user/**").permitAll() //ajax 요청명도 여기다 써줘야한다
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated());


        return http.build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
