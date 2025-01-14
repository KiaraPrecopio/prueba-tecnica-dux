package com.kiarap.pruebatecnica.api;

import com.kiarap.pruebatecnica.dto.UserRequestDTO;
import com.kiarap.pruebatecnica.dto.LoginResponseDTO;
import reactor.core.publisher.Mono;

public interface ILoginService {

    Mono<LoginResponseDTO> login(UserRequestDTO userRequestDTO);

    Mono<Void> register(UserRequestDTO userRequestDTO);
}
