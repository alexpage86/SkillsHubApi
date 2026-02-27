package com.spotevent.user.domain.model;

import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document(collection = "photos")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Photo {
    @Id
    private String id;
    
    private String title;
        
    private Binary image;

    private Integer userId;
    
    private String contentType; // type MIME (image/jpeg, image/png...)
}
