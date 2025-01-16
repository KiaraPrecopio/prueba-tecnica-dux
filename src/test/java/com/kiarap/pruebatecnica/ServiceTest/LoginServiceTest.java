package com.kiarap.pruebatecnica.ServiceTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.kiarap.pruebatecnica.dto.LoginResponseDTO;
import com.kiarap.pruebatecnica.dto.UserRequestDTO;
import com.kiarap.pruebatecnica.exception.CustomException;
import com.kiarap.pruebatecnica.repository.UsuariosRepository;
import com.kiarap.pruebatecnica.service.LoginService;
import com.kiarap.pruebatecnica.utils.CustomUserDetailsService;
import com.kiarap.pruebatecnica.utils.JwtService;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;
    @Mock
    private UsuariosRepository usuariosRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void validLogin() {
        final String username = "testUser";
        final String rawPassword = "testPassword";
        final String encodedPassword = "$2a$10$ekEUkC0kX/PREZSwDdws.O5GB1nhH57z37OO1WfGhyPEyfLNJX7oa";

        final UserDetails userDetails = Mockito.mock(UserDetails.class);

        Mockito.when(userDetails.getUsername()).thenReturn(username);
        Mockito.when(userDetails.getPassword()).thenReturn(encodedPassword);
        Mockito.when(customUserDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        Mockito.when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);
        Mockito.when(jwtService.generateToken(username)).thenReturn("testToken");

        final Mono<LoginResponseDTO> response = loginService.login(new UserRequestDTO(username, rawPassword));
        final String generatedToken = response.block().getToken();

        Assertions.assertNotNull(generatedToken);

        Mockito.verify(customUserDetailsService).loadUserByUsername(username);
        Mockito.verify(passwordEncoder).matches(rawPassword, encodedPassword);
        Mockito.verify(jwtService).generateToken(username);
    }

    @Test
    void invalidUsername() {
        final String username = "testUser";

        Mockito.when(customUserDetailsService.loadUserByUsername(username)).thenReturn(null);

        Assertions.assertThrows(CustomException.class, () -> 
            loginService.login(new UserRequestDTO(username, "testPassword")).block()
        );

        Mockito.verify(customUserDetailsService).loadUserByUsername(username);
        Mockito.verifyNoInteractions(passwordEncoder, jwtService);
    }

    @Test
    void invalidPassword() {
        final String username = "testUser";
        final String rawPassword = "testPassword";
        final String encodedPassword = "$2a$10$ekEUkC0kX/PREZSwDdws.O5GB1nhH57z37OO1WfGhyPEyfLNJX7oa";

        final UserDetails userDetails = Mockito.mock(UserDetails.class);

        Mockito.when(userDetails.getPassword()).thenReturn(encodedPassword);
        Mockito.when(customUserDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        Mockito.when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);

        Assertions.assertThrows(CustomException.class, () -> 
            loginService.login(new UserRequestDTO(username, rawPassword)).block()
        );

        Mockito.verify(customUserDetailsService).loadUserByUsername(username);
        Mockito.verify(passwordEncoder).matches(rawPassword, encodedPassword);
        Mockito.verifyNoInteractions(jwtService);
    }
}

