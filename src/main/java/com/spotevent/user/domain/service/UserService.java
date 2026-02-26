package com.spotevent.user.domain.service;

import java.util.Optional;

import com.spotevent.user.infrastructure.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spotevent.user.domain.model.User;
import com.spotevent.user.domain.persistence.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final PasswordEncoder passwordEncoder;
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User getUser(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	@Transactional
	public User updateUser(Integer id, User userChanges){
		// 1. Vérifier l'existence et récupérer l'état actuel (fail-fast)
		User existingUser = userRepository.findById(id).orElseThrow();
		// 2. Mettre à jour uniquement les champs autorisés
		userMapper.updateEntityFromDomain(userChanges, existingUser);

		// 3. Sauvegarder l'objet fusionné
		return userRepository.save(existingUser);
	}
	
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}
	
	public boolean authenticate(String username, String password) {
		Optional<User> optUser = userRepository.findByUsername(username);
		
		if(optUser.isPresent()) {
			User user = optUser.get();
			
			if(!user.getUsername().equals(username)){
	            throw new UsernameNotFoundException("User does not exist in the database");
	        }

	        if (!passwordEncoder.matches(password, user.getPassword())) {
	            throw  new BadCredentialsException("The password is incorrect");
	        }
	        
	        return true;
		}
        return  false;
    }
	
	public Optional<User> findByUsername(String username) {
		return Optional.of(userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username : " + username)));
	}

	public User findById(Integer userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User save(User user) {
		return userRepository.save(user);
	}
}
