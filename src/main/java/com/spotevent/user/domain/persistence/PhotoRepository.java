package com.spotevent.user.domain.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spotevent.user.domain.model.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String> {

}
