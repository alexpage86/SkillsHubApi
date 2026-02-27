package com.spotevent.auth.domain.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

	private final JwtEncoder jwtEncoder;
	
	public String generateToken(Authentication authentication) {
		Instant now = Instant.now();

		// On récupère les rôles pour les mettre dans le claim "scope"
		String scope = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));

		// Définir explicitement l'algorithme HS256 dans les headers
		JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();

		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("self")
				.issuedAt(now)
				.expiresAt(now.plus(1, ChronoUnit.DAYS)) // ====> Durée de vie du token
				.subject(authentication.getName())
				.claim("scope", scope)
				.build();

		// Passer le header et les claims aux paramètres d'encodage
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(jwsHeader, claims);
		return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
	}
}
