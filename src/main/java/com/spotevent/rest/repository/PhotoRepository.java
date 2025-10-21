package com.spotevent.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spotevent.rest.entity.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String> {

}
