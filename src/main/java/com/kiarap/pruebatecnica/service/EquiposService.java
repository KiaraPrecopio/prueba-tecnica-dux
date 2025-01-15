package com.kiarap.pruebatecnica.service;

import com.kiarap.pruebatecnica.api.IEquiposService;
import com.kiarap.pruebatecnica.dto.EquiposRequestDTO;
import com.kiarap.pruebatecnica.dto.EquiposResponseDTO;
import com.kiarap.pruebatecnica.model.Equipos;
import com.kiarap.pruebatecnica.repository.EquiposRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<EquiposResponseDTO> getAll() {
        return equiposRepository.findAll().stream()
            .map(this::buildResponse)
            .collect(Collectors.toList());
    }

    @Override
    public EquiposResponseDTO getById(final Long id) {
        return equiposRepository.findById(id)
            .map(this::buildResponse)
            .orElseThrow(() -> new RuntimeException("No se encontró el equipo"));
    }

    @Override
    public List<EquiposResponseDTO> getByName(final String name) {
        return equiposRepository.findByName(name).stream()
            .map(this::buildResponse)
            .collect(Collectors.toList());
    }

    @Override
    public EquiposResponseDTO save(final EquiposRequestDTO equiposRequestDTO) {
        System.out.println("pais: " + equiposRequestDTO.getPais());
        String errorMessage = validateEquipo(equiposRequestDTO.toEntity());
        if (errorMessage != null) {
            throw new IllegalArgumentException(errorMessage);
        }
        Equipos equipo = Equipos.builder().nombre(equiposRequestDTO.getNombre()).liga(equiposRequestDTO.getLiga()).pais(equiposRequestDTO.getPais()).build();
        equiposRepository.save(equipo);
        return buildResponse(equipo);
    }

    @Override
    public EquiposResponseDTO update(final Long id, final EquiposRequestDTO equiposRequestDTO) {
        Equipos equipo = equiposRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el equipo"));
        equipo.setNombre(equiposRequestDTO.getNombre() != null ? equiposRequestDTO.getNombre() : equipo.getNombre());
        equipo.setLiga(equiposRequestDTO.getLiga() != null ? equiposRequestDTO.getLiga() : equipo.getLiga());
        equipo.setPais(equiposRequestDTO.getPais() != null ? equiposRequestDTO.getPais() : equipo.getPais());
        String errorMessage = validateEquipo(equipo);
        if (errorMessage != null) {
            throw new IllegalArgumentException(errorMessage);
        }
        Equipos updatedEquipo = equiposRepository.save(equipo);
        return buildResponse(updatedEquipo);
    }

    private String validateEquipo(final Equipos equipo) {
        final boolean equipoExists = equiposRepository.findDuplicatedEquipos(equipo.getNombre());
        if (equipoExists) {
            return "El equipo ya existe";
        }
        return null;
    }

    @Override
    public void delete(final Long id) {
        Equipos equipo = equiposRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el equipo"));
        equiposRepository.delete(equipo);
    }
}