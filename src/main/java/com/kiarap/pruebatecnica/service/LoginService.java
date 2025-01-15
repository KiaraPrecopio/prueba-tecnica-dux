package com.kiarap.pruebatecnica.service;

import com.kiarap.pruebatecnica.api.ILoginService;
import com.kiarap.pruebatecnica.dto.UserRequestDTO;
import com.kiarap.pruebatecnica.dto.LoginResponseDTO;
import com.kiarap.pruebatecnica.utils.CustomUserDetailsService;
import com.kiarap.pruebatecnica.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public Mono<LoginResponseDTO> login(final UserRequestDTO userRequestDTO) {
        return Mono.justOrEmpty(customUserDetailsService.loadUserByUsername(userRequestDTO.getUsername()))
                .switchIfEmpty(Mono.error(new RuntimeException("Invalid username")))
                .flatMap(userDetails -> {
                    if (passwordEncoder.matches(userRequestDTO.getPassword(), userDetails.getPassword())) {
                        return Mono.just(jwtService.generateToken(userDetails.getUsername()))
                                .flatMap(token -> Mono.just(new LoginResponseDTO(token)));
                    } else {
                        return Mono.error(new RuntimeException("Invalid password"));
                    }
                });
    }
}
