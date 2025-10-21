package com.spotevent.rest.service;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spotevent.rest.entity.User;
import com.spotevent.rest.repository.UserRepository;

@Service
public class UserService {
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
		this.userRepository = userRepository;
	}
	
	public User addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User getUser(Integer id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
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

	        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
	            throw  new BadCredentialsException("The password is incorrect");
	        }
	        
	        return true;
		}

        

        return  false;
    }
	
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findById(Integer userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User save(User user) {
		return userRepository.save(user);
	}
}
