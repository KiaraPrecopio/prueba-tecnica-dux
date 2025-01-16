package com.kiarap.pruebatecnica.controller;

import com.kiarap.pruebatecnica.controller.path.RestRoutes;
import com.kiarap.pruebatecnica.dto.EquiposRequestDTO;
import com.kiarap.pruebatecnica.dto.EquiposResponseDTO;
import com.kiarap.pruebatecnica.utils.ErrorResponse;
import com.kiarap.pruebatecnica.api.IEquiposService;
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
@Tag(name = "Equipos", description = "Peticiones relacionadas a los equipos.")
@SecurityRequirement(name = "JWT")
public class EquiposController {

    @Autowired
    private IEquiposService equiposService;

    @GetMapping(value = RestRoutes.EQUIPOS.EQUIPOS)
    @Operation(summary = "Obtener todos los equipos.",
        description = "Endpoint para obtener todos los equipos presentes en la base de datos.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = EquiposResponseDTO.class))),
        }
    )
     public ResponseEntity<List<EquiposResponseDTO>> obtenerEquipos() {
        return ResponseEntity.ok(equiposService.getAll());
    }

    @GetMapping(value = RestRoutes.EQUIPOS.EQUIPOS_PATH)
    @Operation(summary = "Obtener un equipo.",
        description = "Obtener un equipo existente por su Id.",
        parameters = {
                @Parameter(name = "id", description = "Id del equipo", required = true, example = "1")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = EquiposResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    public ResponseEntity<EquiposResponseDTO> obtenerEquipo(@PathVariable final Long id) {
        return ResponseEntity.ok(equiposService.getById(id));
    }

    @GetMapping(value = RestRoutes.EQUIPOS.EQUIPOS_SEARCH)
    @Operation(summary = "Buscar por nombre",
        description = "Buscar un equipo por su nombre en la base de datos.",
        parameters = {
            @Parameter(name = "nombre", description = "Nombre del equipo.", required = true, example = "Real Madrid")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = EquiposResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    public ResponseEntity<List<EquiposResponseDTO>> buscarEquipo(@RequestParam final String nombre) {
        return ResponseEntity.ok(equiposService.getByName(nombre));
    }

    @PostMapping(value = RestRoutes.EQUIPOS.EQUIPOS)
    @Operation(summary = "Crear un equipo.",
        description = "Crear un equipo nuevo en la base de datos.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Cuerpo de la petición."),
        responses = {
            @ApiResponse(responseCode = "201", description = "Equipo creado exitosamente.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = EquiposResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "La solicitud es inválida.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    public ResponseEntity<EquiposResponseDTO> crearEquipo(@RequestBody final EquiposRequestDTO dto) {
        return ResponseEntity.status(201).body(equiposService.save(dto));
    }

    @PutMapping(value = RestRoutes.EQUIPOS.EQUIPOS_PATH)
    @Operation(summary = "Actualizar un equipo.",
        description = "Actualizar un equipo existente en la base de datos.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Cuerpo de la petición."),
        parameters = {
            @Parameter(name = "id", description = "Id del equipo.", required = true, example = "1")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = EquiposResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)))
        }
    )
    public ResponseEntity<EquiposResponseDTO> actualizarEquipo(@PathVariable final Long id, @RequestBody final EquiposRequestDTO dto) {
        return ResponseEntity.ok(equiposService.update(id, dto));
    }

    @DeleteMapping(value = RestRoutes.EQUIPOS.EQUIPOS_PATH)
    @Operation(summary = "Eliminar un equipo.",
            description = "Eliminar un equipo existente en la base de datos.",
            parameters = {
                    @Parameter(name = "id", description = "Id del equipo.", required = true, example = "1")
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Operación exitosa."),
                @ApiResponse(responseCode = "404", description = "Equipo no encontrado.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<Void> eliminarEquipo(@PathVariable final Long id) {
        equiposService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
