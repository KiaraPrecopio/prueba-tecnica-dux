package com.kiarap.pruebatecnica.api;

import com.kiarap.pruebatecnica.dto.EquiposRequestDTO;
import com.kiarap.pruebatecnica.dto.EquiposResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEquiposService {

    Flux<EquiposResponseDTO> getAll();

    Mono<EquiposResponseDTO> getById(Long id);

    Mono<EquiposResponseDTO> getByName(String name);

    Mono<EquiposResponseDTO> save(EquiposRequestDTO equiposRequestDTO);

    Mono<EquiposResponseDTO> update(Long id, EquiposRequestDTO equiposRequestDTO);

    Mono<Void> delete(Long id);
}
