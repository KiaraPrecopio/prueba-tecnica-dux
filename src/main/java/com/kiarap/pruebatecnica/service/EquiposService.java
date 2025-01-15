package com.kiarap.pruebatecnica.service;

import com.kiarap.pruebatecnica.dto.EquiposRequestDTO;
import com.kiarap.pruebatecnica.dto.EquiposResponseDTO;
import com.kiarap.pruebatecnica.exception.CustomException;
import com.kiarap.pruebatecnica.model.Equipos;
import com.kiarap.pruebatecnica.repository.EquiposRepository;
import com.kiarap.pruebatecnica.api.IEquiposService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EquiposService implements IEquiposService {

    private static final String EQUIPO_NOT_FOUND_MESSAGE = "Equipo no encontrado";

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
    public List<EquiposResponseDTO> getAll() {
        return equiposRepository.findAll().stream()
            .map(this::buildResponse)
            .collect(Collectors.toList());
    }

    @Override
    public EquiposResponseDTO getById(final Long id) {
        return equiposRepository.findById(id)
            .map(this::buildResponse)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, EQUIPO_NOT_FOUND_MESSAGE));
    }

    @Override
    public List<EquiposResponseDTO> getByName(final String name) {
        final List<Equipos> equipos = equiposRepository.findByName(name);
        if (equipos.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("No se encuentran presentes equipos con el nombre '%s'", name));
        }
        return equipos.stream()
            .map(this::buildResponse)
            .collect(Collectors.toList());
    }

    @Override
    public EquiposResponseDTO save(final EquiposRequestDTO equiposRequestDTO) {
        if (equiposRequestDTO.getNombre() == null || equiposRequestDTO.getLiga() == null || equiposRequestDTO.getPais() == null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "La solicitud es inválida");
        }
        validateNameEquipo(equiposRequestDTO.getNombre(), null);
        final Equipos equipo = Equipos.builder()
                        .nombre(equiposRequestDTO.getNombre())
                        .liga(equiposRequestDTO.getLiga())
                        .pais(equiposRequestDTO.getPais()).build();
        equiposRepository.save(equipo);
        return buildResponse(equipo);
    }

    @Override
    public EquiposResponseDTO update(final Long id, final EquiposRequestDTO equiposRequestDTO) {
        final Equipos equipo = equiposRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, EQUIPO_NOT_FOUND_MESSAGE));
        equipo.setNombre(equiposRequestDTO.getNombre() != null ? equiposRequestDTO.getNombre() : equipo.getNombre());
        equipo.setLiga(equiposRequestDTO.getLiga() != null ? equiposRequestDTO.getLiga() : equipo.getLiga());
        equipo.setPais(equiposRequestDTO.getPais() != null ? equiposRequestDTO.getPais() : equipo.getPais());
        validateNameEquipo(equipo.getNombre(), id);
        final Equipos updatedEquipo = equiposRepository.save(equipo);
        return buildResponse(updatedEquipo);
    }

    private void validateNameEquipo(final String nombre, final Long id) {
        final boolean equipoExists = equiposRepository.isNameDuplicated(nombre, id);
        if (equipoExists) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "La solicitud es inválida");
        }
    }

    @Override
    public void delete(final Long id) {
        final Equipos equipo = equiposRepository.findById(id)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, EQUIPO_NOT_FOUND_MESSAGE));
        equiposRepository.delete(equipo);
    }
}