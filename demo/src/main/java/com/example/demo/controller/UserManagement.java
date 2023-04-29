package com.example.demo.controller;

import org.hibernate.jpa.internal.util.PersistenceUtilHelper.AttributeExtractionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bean.User;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;


@Controller
public class UserManagement {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/userManagement")
	public String userManagement(User user, Model model) {
		model.addAttribute("user", user);
		return "userManagement";
	}
	
	

	
	
	@PostMapping("/userManagement")
	public String add(@Valid User user, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "userManagement";
			
		}else {
//			userRepository.save(user);
			userService.addUser(user);
			System.out.println(user.getNom());
		}
		//redirect = redirige vers l'url donc /.../... ou / ou /..
		return "redirect:/";
		
	}
	
	
	@GetMapping("/userManagement/update/{identifiant}")
    public String UserUpdate(@PathVariable(value = "identifiant") int id, @Valid User userDetail)
    		throws AttributeExtractionException{
		
		//Optional<User> user = userRepository.findById(id);
		User user = userService.findById(id).orElseThrow();
		
		AttributeNotFoundException("Aucun user avec l'id :: " + id);
		
		user.setNom(userDetail.getNom());
		user.setPrenom(userDetail.getPrenom());
		user.setEmail(userDetail.getEmail());
		
    	userService.addUser(user);

        
        return "redirect:/userManagement";
    }





	private void AttributeNotFoundException(String string) {
		// TODO Auto-generated method stub
		
	}
	
	
}
