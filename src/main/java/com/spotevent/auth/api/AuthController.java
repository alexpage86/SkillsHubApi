package com.spotevent.auth.api;

import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.spotevent.auth.api.dto.AuthRequest;
import com.spotevent.auth.domain.service.TokenService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final TokenService tokenService;
	
	private final AuthenticationManager authenticationManager;

	@GetMapping("/user")
	public String getUser() {
		return "Welcome user !";
	}

	@GetMapping("/admin")
	public String getAdmin() {
		return "Welcome admin !";
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
		try {
			// 1. Authentification via le manager (qui utilise UserDetailsService)
			Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
							authRequest.username(),
							authRequest.password()
                    )
            );
			// 2. Génération du JWT via TokenService (qui utilise maintenant ton JwtEncoder)
			String token = tokenService.generateToken(auth);

            return ResponseEntity.ok(Map.of("token", token));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("error", "Invalid username or password"));
		}
	}
}
