package com.spotevent.rest.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spotevent.rest.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	/**
	 *  Cherche un utilisateur par son username
	 * @param username
	 * @return
	 */
    Optional<User> findByUsername(String username);

}
