package com.spotevent.rest.security;


import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spotevent.rest.entity.User;
import com.spotevent.rest.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("Username => " + username);
        Optional<User> optUser = userRepository.findByUsername(username);

        if (optUser.isPresent()){
        	return new UserPrincipal(optUser.get());
        } else {
            throw new UsernameNotFoundException("This user does not exist in the database");
        }
    }
}
