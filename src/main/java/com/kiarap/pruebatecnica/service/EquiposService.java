package com.kiarap.pruebatecnica.service;

import com.kiarap.pruebatecnica.api.IEquiposService;
import com.kiarap.pruebatecnica.dto.EquiposRequestDTO;
import com.kiarap.pruebatecnica.dto.EquiposResponseDTO;
import com.kiarap.pruebatecnica.model.Equipos;
import com.kiarap.pruebatecnica.repository.EquiposRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EquiposService implements IEquiposService {

    @Autowired
    private EquiposRepository equiposRepository;

    private EquiposResponseDTO buildResponse(final Equipos equipo) {
        return EquiposResponseDTO.builder()
                .id(equipo.getId())
                .liga(equipo.getLiga())
                .nombre(equipo.getNombre())
                .pais(equipo.getPais())
                .build();
    }

    @Override
    public Flux<EquiposResponseDTO> getAll() {
        return Flux.fromIterable(equiposRepository.findAll())
                .map(this::buildResponse);
    }

    @Override
    public Mono<EquiposResponseDTO> getById(final Long id) {
        return Mono.justOrEmpty(equiposRepository.findById(id))
                .switchIfEmpty(Mono.error(new RuntimeException("No se encontró el equipo")))
                .map(this::buildResponse);
    }

    @Override
    public Mono<EquiposResponseDTO> getByName(final String name) {
        return Mono.justOrEmpty(equiposRepository.findByName(name))
                .switchIfEmpty(Mono.error(new RuntimeException("No se encontró el equipo")))
                .map(this::buildResponse);
    }

    @Override
    public Mono<EquiposResponseDTO> save(final EquiposRequestDTO equiposRequestDTO) {
        return Mono.just(equiposRequestDTO)
                .map(EquiposRequestDTO::toEntity)
                .flatMap(equipo -> {
                    String errorMessage = validateEquipo(equipo);
                    if (errorMessage != null) {
                        return Mono.error(new IllegalArgumentException(errorMessage));
                    }
                    return Mono.just(equipo);
                })
                .map(equiposRepository::save)
                .map(this::buildResponse);
    }

    private String validateEquipo(final Equipos equipo) {
        if (equipo.getNombre() == null || equipo.getNombre().isEmpty()) {
            return "El nombre del equipo no puede estar vacío";
        } else if (equipo.getLiga() == null || equipo.getLiga().isEmpty()) {
            return "La liga del equipo no puede estar vacía";
        } else if (equipo.getPais() == null || equipo.getPais().isEmpty()) {
            return "El país del equipo no puede estar vacío";
        }
        return null;
    }

    @Override
    public Mono<EquiposResponseDTO> update(final Long id, final EquiposRequestDTO equiposRequestDTO) {
        return Mono.justOrEmpty(equiposRepository.findById(id))
                .switchIfEmpty(Mono.error(new RuntimeException("No se encontró el equipo")))
                .map(equipo -> {
                    equipo.setNombre(equiposRequestDTO.getNombre() != null ? equiposRequestDTO.getNombre() : equipo.getNombre());
                    equipo.setLiga(equiposRequestDTO.getLiga() != null ? equiposRequestDTO.getLiga() : equipo.getLiga());
                    equipo.setPais(equiposRequestDTO.getPais() != null ? equiposRequestDTO.getPais() : equipo.getPais());
                    return equipo;
                })
                .flatMap(equipo -> {
                    String errorMessage = validateEquipo(equipo);
                    if (errorMessage != null) {
                        return Mono.error(new IllegalArgumentException(errorMessage));
                    }
                    return Mono.just(equipo);
                })
                .map(equiposRepository::save)
                .map(this::buildResponse);
    }

    @Override
    public Mono<Void> delete(final Long id) {
        return Mono.justOrEmpty(equiposRepository.findById(id))
                .switchIfEmpty(Mono.error(new RuntimeException("No se encontró el equipo")))
                .flatMap(equipos -> {
                    equiposRepository.delete(equipos);
                    return Mono.empty();
                });
    }
}
