package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository ;
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public List <User> listUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}
	
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

}
