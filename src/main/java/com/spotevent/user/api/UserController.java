package com.spotevent.user.api;


import com.spotevent.user.domain.model.Photo;
import com.spotevent.user.domain.service.PhotoService;
import com.spotevent.user.api.dto.UserRequest;
import com.spotevent.user.api.dto.UserResponse;
import com.spotevent.user.domain.usecase.UpdateUserAvatarUseCase;
import com.spotevent.user.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.spotevent.user.domain.model.User;
import com.spotevent.user.domain.service.UserService;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	private final PhotoService photoService;
	private final UpdateUserAvatarUseCase updateUserAvatarUseCase;
	private final UserMapper userMapper;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable("id") Integer id) {
		User user = userService.findById(id);
		return ResponseEntity.ok(userMapper.toResponse(user));
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/register")
	public ResponseEntity<UserResponse> createUser(@RequestBody() UserRequest userRequest){
		User newUser = userService.addUser(userMapper.toEntity(userRequest));
		return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toResponse(newUser));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Integer id, @RequestBody() UserRequest userRequest){
		User newUser = userService.updateUser(id, userMapper.toEntity(userRequest));
		return ResponseEntity.ok().body(userMapper.toResponse(newUser));
	}
	
	@GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
		User connectedUser = userService.findByUsername(username).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with username : " + username)
		);
        return ResponseEntity.ok(userMapper.toResponse(connectedUser));
    }

	/**
	 * Upload / replace profile photo
	 */
	@PostMapping("/upload/{userId}")
	public ResponseEntity<String> uploadPhoto(@PathVariable Integer userId,
											  @RequestParam("file") MultipartFile file) throws IOException {

		String photoId = updateUserAvatarUseCase.execute(userId, file);
		return ResponseEntity.ok("Photo uploadée avec succès, id = " + photoId);
	}

	@GetMapping("/avatar/{userId}")
	public ResponseEntity<byte[]> getUserAvatar(@PathVariable Integer userId) {
		User user = userService.findById(userId);

		if (user.getPhotoProfileId() == null) {
			return ResponseEntity.notFound().build();
		}

		Photo photo = photoService.findById(user.getPhotoProfileId());

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, photo.getContentType() != null ? photo.getContentType() : MediaType.IMAGE_JPEG_VALUE)
				.body(photo.getImage().getData());
	}
	
}
