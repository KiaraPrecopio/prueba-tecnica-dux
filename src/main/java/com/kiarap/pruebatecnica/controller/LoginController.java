package com.kiarap.pruebatecnica.controller;

import com.kiarap.pruebatecnica.api.ILoginService;
import com.kiarap.pruebatecnica.controller.path.RestRoutes;
import com.kiarap.pruebatecnica.dto.UserRequestDTO;
import com.kiarap.pruebatecnica.dto.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Tag(name = "Login", description = "Login API")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @GetMapping(RestRoutes.LOGIN.LOGIN)
    @Operation(summary = "Login de usuario",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Login Body Request",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserRequestDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login exitoso"),
                    @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
            })
    public ResponseEntity<Mono<LoginResponseDTO>> login(@RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(loginService.login(dto));
    }

    @PostMapping(RestRoutes.LOGIN.REGISTER)
    @Operation(summary = "Registro de usuario",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Registro Body Request",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserRequestDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Registro exitoso"),
                    @ApiResponse(responseCode = "400", description = "Usuario ya existe")
            })
    public ResponseEntity<Mono<Void>> register(@RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(loginService.register(dto));
    }
}