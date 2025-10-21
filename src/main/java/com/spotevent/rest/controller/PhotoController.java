package com.spotevent.rest.controller;

import java.io.IOException;

import org.bson.types.Binary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spotevent.rest.entity.Photo;
import com.spotevent.rest.entity.User;
import com.spotevent.rest.service.PhotoService;
import com.spotevent.rest.service.UserService;

@RestController
public class PhotoController {
	
	private final PhotoService photoService;
    private final UserService userService;
    
    public PhotoController(PhotoService photoService, UserService userService) {
        this.photoService = photoService;
        this.userService = userService;
    }

    /**
     * Upload / replace profile photo
     */
    @PostMapping("/photos/upload/{userId}")
    public ResponseEntity<?> uploadPhoto(@PathVariable Integer userId,
                                         @RequestParam("file") MultipartFile file) throws IOException {

        // Vérifier que l'utilisateur existe
        User user = userService.findById(userId);

        // Supprimer l'ancienne photo si elle existe
        if (user.getPhotoProfileId() != null) {
            photoService.deleteById(user.getPhotoProfileId());
        }

        // Sauvegarder la nouvelle photo
        Binary binaryImage = new Binary(file.getBytes());
        Photo photo = new Photo(file.getOriginalFilename(), binaryImage, file.getContentType());
        Photo savedPhoto = photoService.save(photo);

        // Mettre à jour le user avec l'id de la nouvelle photo
        user.setPhotoProfileId(savedPhoto.getId());
        userService.updateUser(user);

        return ResponseEntity.ok("Photo uploadée avec succès, id = " + savedPhoto.getId());
    }
    
    @GetMapping("/photos/user/{userId}")
    public ResponseEntity<byte[]> getUserPhoto(@PathVariable Integer userId) {
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
