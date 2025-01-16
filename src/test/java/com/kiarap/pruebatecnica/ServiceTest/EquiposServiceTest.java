package com.kiarap.pruebatecnica.ServiceTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.kiarap.pruebatecnica.dto.EquiposRequestDTO;
import com.kiarap.pruebatecnica.dto.EquiposResponseDTO;
import com.kiarap.pruebatecnica.exception.CustomException;
import com.kiarap.pruebatecnica.model.Equipos;
import com.kiarap.pruebatecnica.repository.EquiposRepository;
import com.kiarap.pruebatecnica.service.EquiposService;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EquiposServiceTest {

    @InjectMocks
    private EquiposService equiposService;
    @Mock
    private EquiposRepository equiposRepository;

    @Test
    void getAll() {
        final Equipos equipo = new Equipos();
        equipo.setNombre("Real Madrid");

        Mockito.when(equiposRepository.findAll()).thenReturn(List.of(equipo));

        final List<EquiposResponseDTO> result = equiposService.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(equipo.getNombre(), result.get(0).getNombre());
        Mockito.verify(equiposRepository).findAll();
    }

    @Test
    void getAllEmpty() {
        Mockito.when(equiposRepository.findAll()).thenReturn(List.of());

        final List<EquiposResponseDTO> result = equiposService.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
        Mockito.verify(equiposRepository).findAll();
    }

    @Test
    void getByIdValid() {
        final Long equipoId = 1L;
        final String nombre = "Real Madrid";
        final Equipos equipo = new Equipos();
        equipo.setId(equipoId);
        equipo.setNombre(nombre);
        
        Mockito.when(equiposRepository.findById(equipoId)).thenReturn(Optional.of(equipo));

        final EquiposResponseDTO result = equiposService.getById(equipoId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(equipoId, result.getId());
        Assertions.assertEquals(nombre, result.getNombre());
        Mockito.verify(equiposRepository).findById(equipoId);
    }

    @Test
    void getByIdInvalid() {
        final Long equipoId = 1L;

        Mockito.when(equiposRepository.findById(equipoId)).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomException.class, () -> {
            equiposService.getById(equipoId);
        });

        Mockito.verify(equiposRepository).findById(equipoId);
    }

    @Test
    void getByNameValid() {
        final String nombre = "Real Madrid";
        final Equipos equipo = new Equipos();
        equipo.setNombre(nombre);

        Mockito.when(equiposRepository.findByName(nombre)).thenReturn(List.of(equipo));

        final List<EquiposResponseDTO> result = equiposService.getByName(nombre);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(nombre, result.get(0).getNombre());
        Mockito.verify(equiposRepository).findByName(nombre);
    }

    @Test
    void getByNameInvalid() {
        final String nombre = "Real Madrid";

        Mockito.when(equiposRepository.findByName(nombre)).thenReturn(List.of());

        Assertions.assertThrows(CustomException.class, () -> {
            equiposService.getByName(nombre);
        });

        Mockito.verify(equiposRepository).findByName(nombre);
    }

    @Test
    void saveValid() {
        final EquiposRequestDTO equiposRequestDTO = new EquiposRequestDTO();
        equiposRequestDTO.setNombre("Real Madrid");
        equiposRequestDTO.setLiga("La Liga");
        equiposRequestDTO.setPais("España");

        final Equipos equipo = new Equipos();
        equipo.setNombre(equiposRequestDTO.getNombre());
        equipo.setLiga(equiposRequestDTO.getLiga());
        equipo.setPais(equiposRequestDTO.getPais());

        Mockito.when(equiposRepository.save(equipo)).thenReturn(equipo);

        final EquiposResponseDTO result = equiposService.save(equiposRequestDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(equiposRequestDTO.getNombre(), result.getNombre());
        Assertions.assertEquals(equiposRequestDTO.getLiga(), result.getLiga());
        Assertions.assertEquals(equiposRequestDTO.getPais(), result.getPais());
        Mockito.verify(equiposRepository).save(equipo);
    }

    @Test
    void saveInvalidContent() {
        final EquiposRequestDTO equiposRequestDTO = new EquiposRequestDTO();
        equiposRequestDTO.setNombre(null);
        equiposRequestDTO.setLiga(null);
        equiposRequestDTO.setPais(null);

        Assertions.assertThrows(CustomException.class, () -> {
            equiposService.save((equiposRequestDTO));
        });

        Mockito.verifyNoInteractions(equiposRepository);
    }

    @Test
    void saveInvalidName() {
        final EquiposRequestDTO equiposRequestDTO = new EquiposRequestDTO();
        equiposRequestDTO.setNombre("Real Madrid");
        equiposRequestDTO.setLiga("La Liga");
        equiposRequestDTO.setPais("España");

        final Equipos equipo = new Equipos();
        equipo.setNombre(equiposRequestDTO.getNombre());
        equipo.setLiga(equiposRequestDTO.getLiga());
        equipo.setPais(equiposRequestDTO.getPais());

        Mockito.when(equiposRepository.isNameDuplicated(equiposRequestDTO.getNombre(), null)).thenReturn(true);

        Assertions.assertThrows(CustomException.class, () -> {
            equiposService.save(equiposRequestDTO);
        });

        Mockito.verify(equiposRepository).isNameDuplicated(equiposRequestDTO.getNombre(), null);
    }

    @Test
    void updateValid() {
        final Long equipoId = 1L;
        final EquiposRequestDTO equiposRequestDTO = new EquiposRequestDTO();
        equiposRequestDTO.setNombre("Real Madrid");
        equiposRequestDTO.setLiga("La Liga");
        equiposRequestDTO.setPais("España");

        final Equipos equipo = new Equipos();
        equipo.setId(equipoId);
        equipo.setNombre(equiposRequestDTO.getNombre());
        equipo.setLiga(equiposRequestDTO.getLiga());
        equipo.setPais(equiposRequestDTO.getPais());

        Mockito.when(equiposRepository.findById(equipoId)).thenReturn(Optional.of(equipo));
        Mockito.when(equiposRepository.save(equipo)).thenReturn(equipo);

        final EquiposResponseDTO result = equiposService.update(equipoId, equiposRequestDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(equipoId, result.getId());
        Assertions.assertEquals(equiposRequestDTO.getNombre(), result.getNombre());
        Assertions.assertEquals(equiposRequestDTO.getLiga(), result.getLiga());
        Assertions.assertEquals(equiposRequestDTO.getPais(), result.getPais());
        Mockito.verify(equiposRepository).findById(equipoId);
        Mockito.verify(equiposRepository).save(equipo);
    }

    @Test
    void updateInvalidId() {
        final Long equipoId = 1L;
        final EquiposRequestDTO equiposRequestDTO = new EquiposRequestDTO();
        equiposRequestDTO.setNombre(null);
        equiposRequestDTO.setLiga(null);
        equiposRequestDTO.setPais(null);

        Mockito.when(equiposRepository.findById(equipoId)).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomException.class, () -> {
            equiposService.update(equipoId, equiposRequestDTO);
        });

        Mockito.verify(equiposRepository).findById(equipoId);
    }

    @Test
    void updateInvalidName() {
        final Long equipoId = 1L;
        final EquiposRequestDTO equiposRequestDTO = new EquiposRequestDTO();
        equiposRequestDTO.setNombre("Real Madrid");
        equiposRequestDTO.setLiga("La Liga");
        equiposRequestDTO.setPais("España");

        final Equipos equipo = new Equipos();
        equipo.setId(equipoId);
        equipo.setNombre(equiposRequestDTO.getNombre());
        equipo.setLiga(equiposRequestDTO.getLiga());
        equipo.setPais(equiposRequestDTO.getPais());

        Mockito.when(equiposRepository.findById(equipoId)).thenReturn(Optional.of(equipo));
        Mockito.when(equiposRepository.isNameDuplicated(equiposRequestDTO.getNombre(), equipoId)).thenReturn(true);

        Assertions.assertThrows(CustomException.class, () -> {
            equiposService.update(equipoId, equiposRequestDTO);
        });

        Mockito.verify(equiposRepository).findById(equipoId);
        Mockito.verify(equiposRepository).isNameDuplicated(equiposRequestDTO.getNombre(), equipoId);
    }

    @Test
    void deleteValid() {
        final Long equipoId = 1L;
        final Equipos equipo = new Equipos();
        equipo.setId(equipoId);

        Mockito.when(equiposRepository.findById(equipoId)).thenReturn(Optional.of(equipo));

        equiposService.delete(equipoId);

        Mockito.verify(equiposRepository).findById(equipoId);
        Mockito.verify(equiposRepository).delete(equipo);
    }

    @Test
    void deleteInvalid() {
        final Long equipoId = 1L;

        Mockito.when(equiposRepository.findById(equipoId)).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomException.class, () -> {
            equiposService.delete(equipoId);
        });

        Mockito.verify(equiposRepository).findById(equipoId);
    }
}