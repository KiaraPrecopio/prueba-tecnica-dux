package com.kiarap.pruebatecnica.controller;

import com.kiarap.pruebatecnica.controller.path.RestRoutes;
import com.kiarap.pruebatecnica.dto.EquiposResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Equipos", description = "Operaciones relacionadas con los equipos.")
@SecurityRequirement(name="Bearer Authentication")
public class EquiposController {

    @GetMapping(value = RestRoutes.EQUIPOS.EQUIPOS)
    @Operation(summary = "Obtener todos los equipos.",
            description = "Obtener todos los equipos existentes.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa."),
            }
    )
    public ResponseEntity<EquiposResponseDTO> obtenerEquipos() {
        return ResponseEntity.ok(new EquiposResponseDTO());
    }

}
