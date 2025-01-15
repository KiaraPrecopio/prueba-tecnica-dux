package com.kiarap.pruebatecnica.api;

import java.util.List;

import com.kiarap.pruebatecnica.dto.EquiposRequestDTO;
import com.kiarap.pruebatecnica.dto.EquiposResponseDTO;

public interface IEquiposService {

    List<EquiposResponseDTO> getAll();

    EquiposResponseDTO getById(final Long id);

    List<EquiposResponseDTO> getByName(final String name);

    EquiposResponseDTO save(final EquiposRequestDTO equiposRequestDTO);

    EquiposResponseDTO update(final Long id, final EquiposRequestDTO equiposRequestDTO);

    void delete(final Long id);
}
