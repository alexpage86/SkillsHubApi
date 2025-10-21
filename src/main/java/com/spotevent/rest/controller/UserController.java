package com.spotevent.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spotevent.rest.entity.User;
import com.spotevent.rest.service.UserService;

@RestController
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable("id") Integer id) {
		return userService.getUser(id);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/users/register")
	public ResponseEntity<User> createUser(@RequestBody() User user){
		User newUser = userService.addUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@RequestBody() User user){
		User newUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	@GetMapping("/users/me")
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userService.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
	
}
