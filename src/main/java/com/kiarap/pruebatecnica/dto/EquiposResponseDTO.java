package com.kiarap.pruebatecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para la respuesta de los equipos")
public class EquiposResponseDTO {

    @Schema(description = "Id del equipo", example = "1")
    private Long id;

    @Schema(description = "Nombre del equipo", example = "Real Madrid")
    private String nombre;

    @Schema(description = "Liga del equipo", example = "La Liga")
    private String liga;

    @Schema(description = "País del equipo" , example = "España")
    private String pais;
}
