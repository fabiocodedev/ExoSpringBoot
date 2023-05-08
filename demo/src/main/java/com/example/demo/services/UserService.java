package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.bean.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	
	public String addUser(User user, BindingResult bindingResult) {
		
		if (userRepository.findByEmail (user.getEmail ()) != null ) {
			bindingResult.addError (new FieldError ("user"," email","Le mail existe deja...."));
			System.out.println("IF DU SERVICE");
			return ("/userManagement");
		}
		else {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        user.setPassword(encoder.encode(user.getPassword()));
	        userRepository.save(user);
	        System.out.println("ELSE DU SERVICE");
	        return ("/");
		}
	}
	
	
	public User getUserByEmail(String email) {
		
		return userRepository.findByEmail(email);
		
	}
		
//		public boolean addCustomer(Customer customer, BindingResult bindingResult) {
//			
//			
//
//				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//				customer.setPassword(encoder.encode(customer.getPassword()));
//				customerRepository.save(customer);
//				System.out.println("ELSE DU SERVICE");
//				return true;
//			
//    }
	
}
