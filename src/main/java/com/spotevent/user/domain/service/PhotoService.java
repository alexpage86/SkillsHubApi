package com.spotevent.user.domain.service;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spotevent.user.domain.model.Photo;
import com.spotevent.user.domain.persistence.PhotoRepository;

@Service
@RequiredArgsConstructor
public class PhotoService {
	
	private final PhotoRepository photoRepo;
	
	public String addPhoto(String title, MultipartFile file) throws IOException { 
        Photo newPhoto = Photo.builder()
				.title(title)
				.image(new Binary(BsonBinarySubType.BINARY, file.getBytes()))
				.build();
        Photo savedphoto = photoRepo.insert(newPhoto);
		return savedphoto.getId();
    }

    public Photo getPhoto(String id) { 
        return photoRepo.findById(id).orElse(null);
    }

	public void deleteById(String photoProfileId) {
		photoRepo.deleteById(photoProfileId);
	}

	public Photo save(Photo photo) {
		return photoRepo.save(photo);
	}

	public Photo findById(String photoProfileId) {
		return photoRepo.findById(photoProfileId).orElseThrow(
                () -> new RuntimeException("Photo introuvable : " + photoProfileId)
        );
	}

}
