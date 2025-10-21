package com.spotevent.rest.entity;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "photos")
public class Photo {
    @Id
    private String id;
    
    private String title;
        
    private Binary image;
    
    private String contentType; // type MIME (image/jpeg, image/png...)
    
    public Photo() {
    	
    }
    
    public Photo(String title, Binary image, String contentType) {
        this.title = title;
        this.image = image;
        this.contentType = contentType;
    }
    
    public Photo(String title) {
    	this.title = title;
    }
    
    public void setId(String id) {
    	this.id = id;
    }
    
    public String getId() {
    	return this.id;
    }
    
    public void setTitle(String title) {
    	this.id = title;
    }
    
    public String getTitle() {
    	return this.title;
    }
    
    public void setImage(Binary image) {
    	this.image = image;
    }
    
    public Binary getImage() {
    	return this.image;
    }
    
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
