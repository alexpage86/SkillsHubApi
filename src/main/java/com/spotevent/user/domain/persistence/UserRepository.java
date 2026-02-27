package com.spotevent.user.domain.persistence;

import java.util.Optional;

import com.spotevent.event.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spotevent.user.domain.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 *  Cherche un utilisateur par son username
	 * @param username
	 * @return
	 */
    Optional<User> findByUsername(String username);

}
