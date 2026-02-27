package com.spotevent.user.domain.usecase;

import com.spotevent.user.domain.model.Photo;
import com.spotevent.user.domain.model.User;
import com.spotevent.user.domain.service.PhotoService;
import com.spotevent.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UpdateUserAvatarUseCase {
    private final PhotoService photoService;
    private final UserService userService;

    @Transactional
    public String execute(Integer userId, MultipartFile file) throws IOException {
        // 1. Logique métier : créer l'objet photo
        Photo photo = Photo.builder()
                .title(file.getOriginalFilename())
                .image(new Binary(file.getBytes()))
                .contentType(file.getContentType())
                .userId(userId)
                .build();

        Photo savedPhoto = photoService.save(photo);

        // 2. Logique métier : mettre à jour le profil
        User user = userService.findById(userId);

        // 3. Supprimer l'ancienne photo ici si besoin
        if (user.getPhotoProfileId() != null) photoService.deleteById(user.getPhotoProfileId());

        // 4. Lier l'utilisateur à la nouvelle image
        user.setPhotoProfileId(savedPhoto.getId());
        userService.updateUser(userId, user);

        return savedPhoto.getId();
    }

}
