package com.egitim.blogapi.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.egitim.blogapi.core.exception.ResourceNotFoundException;
import com.egitim.blogapi.model.User;
import com.egitim.blogapi.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from database by user_name
		User user = this.userRepository.findByUserMail(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Email : " + username, 0));

		return user;
	}

}
