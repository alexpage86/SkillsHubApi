package com.spotevent.rest.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spotevent.rest.entity.Credentials;
import com.spotevent.rest.service.JWTService;

import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {

	private final JWTService jwtService;
	
	private final AuthenticationManager authenticationManager;

	public LoginController(JWTService jwtService, AuthenticationManager authenticationManager) {
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	@GetMapping("/user")
	public String getUser() {
		return "Welcome user !";
	}

	@GetMapping("/admin")
	public String getAdmin() {
		return "Welcome admin !";
	}

	@PostMapping("/token")
	public String getToken(Authentication authentication) {
		String token = jwtService.generateToken(authentication);
		return token;
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Credentials loginRequest, HttpSession httpSession) {
		try {
			Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
			
			String token = jwtService.generateToken(auth);
            return ResponseEntity.ok(Map.of("token", token));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An unknown error occurred"));
		}
	}

}
