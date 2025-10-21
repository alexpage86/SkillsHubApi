package com.spotevent.rest.service;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spotevent.rest.entity.Photo;
import com.spotevent.rest.repository.PhotoRepository;

@Service
public class PhotoService {
	
	private final PhotoRepository photoRepo;
	
	public PhotoService(PhotoRepository photoRepo) {
		this.photoRepo = photoRepo;
	}
	
	public String addPhoto(String title, MultipartFile file) throws IOException { 
        Photo photo = new Photo(title); 
        photo.setImage(
          new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
        photo = photoRepo.insert(photo); return photo.getId(); 
    }

    public Photo getPhoto(String id) { 
        return photoRepo.findById(id).get(); 
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
