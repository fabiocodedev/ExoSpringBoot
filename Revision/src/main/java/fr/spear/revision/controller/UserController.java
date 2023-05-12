package fr.spear.revision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.spear.revision.Bean.User;
import fr.spear.revision.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("/register")
	public String registerPage() {
		return ("user/form");
	}
	
	@PostMapping("/register")
	// Validated permet de valider des données d'un form
	public String register(@Validated User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			bindingResult.getFieldError();
			return "user/form";
		}else {
			userService.createUser(user);
			System.out.println("User cree !");
			return "redirect:/";
		}
			
	}
	
	@GetMapping("/login")
	public String login(User user) {
		return "user/login";
	}
	
	
}
