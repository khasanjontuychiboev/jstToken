package com.jst.jsttokenservice.config;

import com.jst.jsttokenservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

import static com.jst.jsttokenservice.entity.enums.PermissionEnum.*;
import static com.jst.jsttokenservice.entity.enums.RoleEnum.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/api/v1/sell").permitAll()
                                .requestMatchers(GET,"/api/v1/document/**").hasAnyRole(ACCEPTOR.name(), SELLER.name())
                                .requestMatchers(POST,"/api/v1/document/**").hasAnyAuthority(DOCUMENT_CREATE.name())
                                .requestMatchers(PUT,"/api/v1/document/**").hasAnyAuthority(DOCUMENT_UPDATE.name())
                                .requestMatchers(DELETE,"/api/v1/document/**").hasAnyAuthority(DOCUMENT_DELETE.name())
                                .anyRequest()
                                .authenticated()
                )
        ;

        return http.build();
    }



    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> userDetails = new ArrayList<>();
        userRepository.findAll().stream()
                .peek(appUser -> {
                    userDetails.add(User.builder()
                            .username(appUser.getUsername())
                            .password(passwordEncoder.encode(appUser.getPassword()))
                            .authorities(appUser.getAuthorities())
                            .build());
                }).forEach(System.out::println);

        return new InMemoryUserDetailsManager(userDetails);
    }
}
