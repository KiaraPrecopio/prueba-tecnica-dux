package com.kiarap.pruebatecnica.controller;

import com.kiarap.pruebatecnica.api.IEquiposService;
import com.kiarap.pruebatecnica.controller.path.RestRoutes;
import com.kiarap.pruebatecnica.dto.EquiposRequestDTO;
import com.kiarap.pruebatecnica.dto.EquiposResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Equipos", description = "Operaciones relacionadas a los equipos.")
@SecurityRequirement(name = "JWT")
public class EquiposController {

    @Autowired
    private IEquiposService equiposService;

    @GetMapping(value = RestRoutes.EQUIPOS.EQUIPOS)
    @Operation(summary = "Obtener todos los equipos.",
            description = "Obtener todos los equipos existentes.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquiposResponseDTO.class))),
            }
    )
     public ResponseEntity<List<EquiposResponseDTO>> obtenerEquipos() {
        return ResponseEntity.ok(equiposService.getAll());
    }

    @GetMapping(value = RestRoutes.EQUIPOS.EQUIPOS_PATH)
    @Operation(summary = "Obtener un equipo.",
            description = "Obtener un equipo existente.",
            parameters = {
                    @Parameter(name = "id", description = "Identificador del equipo.", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquiposResponseDTO.class))),
                @ApiResponse(responseCode = "404", description = "Equipo no encontrado.")
            }
    )
    public ResponseEntity<EquiposResponseDTO> obtenerEquipo(@PathVariable final Long id) {
        return ResponseEntity.ok(equiposService.getById(id));
    }

    @GetMapping(value = RestRoutes.EQUIPOS.EQUIPOS_SEARCH)
    @Operation(summary = "Buscar equipo por nombre",
            description = "Buscar un equipo por su nombre.",
            parameters = {
                    @Parameter(name = "nombre", description = "Nombre del equipo.", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquiposResponseDTO.class))),
                @ApiResponse(responseCode = "404", description = "Equipo no encontrado.")
            }
    )
    public ResponseEntity<List<EquiposResponseDTO>> buscarEquipo(@RequestParam final String nombre) {
        return ResponseEntity.ok(equiposService.getByName(nombre));
    }

    @PostMapping(value = RestRoutes.EQUIPOS.EQUIPOS)
    @Operation(summary = "Crear un equipo.",
            description = "Crear un equipo nuevo.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Cuerpo de la petición."),
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquiposResponseDTO.class))),
                @ApiResponse(responseCode = "400", description = "Equipo ya existe.")
            }
    )
    public ResponseEntity<EquiposResponseDTO> crearEquipo(@RequestBody final EquiposRequestDTO dto) {
        return ResponseEntity.ok(equiposService.save(dto));
    }

    @PutMapping(value = RestRoutes.EQUIPOS.EQUIPOS_PATH)
    @Operation(summary = "Actualizar un equipo.",
            description = "Actualizar un equipo existente.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Cuerpo de la petición."),
            parameters = {
                    @Parameter(name = "id", description = "Identificador del equipo.", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquiposResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Equipo no encontrado.")
            }
    )
    public ResponseEntity<EquiposResponseDTO> actualizarEquipo(@PathVariable final Long id, @RequestBody final EquiposRequestDTO dto) {
        return ResponseEntity.ok(equiposService.update(id, dto));
    }

    @DeleteMapping(value = RestRoutes.EQUIPOS.EQUIPOS_PATH)
    @Operation(summary = "Eliminar un equipo.",
            description = "Eliminar un equipo existente.",
            parameters = {
                    @Parameter(name = "id", description = "Identificador del equipo.", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Successful operation."),
                @ApiResponse(responseCode = "404", description = "Equipo no encontrado.")
            }
    )
    public ResponseEntity<Void> eliminarEquipo(@PathVariable final Long id) {
        equiposService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
