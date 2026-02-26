package com.spotevent.auth.api.dto;

public record AuthRequest (
		String username,
		String password
){}
