package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;

@Configuration
public class SecurityConfig {

    // 1. Bean สำหรับเข้ารหัสรหัสผ่าน
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Bean สำหรับสร้างผู้ใช้เริ่มต้นในหน่วยความจำ (In-memory User)
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // ผู้ใช้ทดสอบ: Username='user', Password='password'
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    // 3. การตั้งค่าสิทธิ์การเข้าถึง (Access Control)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // *** ส่วนที่แก้ไข: เพิ่ม "/home" เข้าไปใน permitAll() ชั่วคราว ***
                        .requestMatchers("/login", "/register", "/home").permitAll()

                        // ส่วนที่เหลือต้องล็อกอิน
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                );

        return http.build();
    }
}