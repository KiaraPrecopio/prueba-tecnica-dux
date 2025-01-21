package com.kiarap.pruebatecnica.service;

import com.kiarap.pruebatecnica.dto.UserRequestDTO;
import com.kiarap.pruebatecnica.exception.CustomException;
import com.kiarap.pruebatecnica.api.ILoginService;
import com.kiarap.pruebatecnica.dto.LoginResponseDTO;
import com.kiarap.pruebatecnica.utils.CustomUserDetailsService;
import com.kiarap.pruebatecnica.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.UNAUTHORIZED, "User with username %s not found", userRequestDTO.getUsername())))
                .flatMap(userDetails -> {
                    if (passwordEncoder.matches(userRequestDTO.getPassword(), userDetails.getPassword())) {
                        return Mono.just(jwtService.generateToken(userDetails.getUsername()))
                                .map(LoginResponseDTO::new);
                    } else {
                        return Mono.error(new CustomException(HttpStatus.UNAUTHORIZED, "The password is incorrect"));
                    }
                });
    }
}
