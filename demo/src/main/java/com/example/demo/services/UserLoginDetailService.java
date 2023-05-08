package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.bean.UserLogin;
import com.example.demo.repository.UserRepository;

@Service
public class UserLoginDetailService implements UserDetailsService{

	@Autowired
	public UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found !");
		}
		return new UserLogin(user);
		
	}
}
