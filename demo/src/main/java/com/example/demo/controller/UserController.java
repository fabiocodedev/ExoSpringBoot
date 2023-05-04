package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bean.User;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/userManagement")
	public String userManagement(User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("roles", roleService.listRole());
		return "user/userManagement";
	}
	
	@PostMapping("/userManagement")
	public String inscription(@Validated User user, BindingResult bindingResult, Model model ) {
	
		if (bindingResult.hasErrors()) {
			System.out.println("has errors" + bindingResult.getFieldErrorCount());
			return ("user/userManagement");
		}else if (userService.addUser(user, bindingResult) != null) {
			System.out.println("Okay");
			return ("redirect:/");
		}else {
			System.out.println("KO");
			return ("user/userManagement");

		}
		
		
//		@PostMapping("/customerManagement")
//		public String inscription(@Validated Customer customer, BindingResult bindingResult, Model model ) {
//			
//			if (bindingResult.hasErrors()) {
//				System.out.println("has errors" + bindingResult.getFieldErrorCount());
//				return ("/customerManagement");
//			}else if (customerService.addCustomer(customer, bindingResult) != null) {
//				System.out.println("existed");
//				return ("/customerManagement");
//			}else {
//				customerService.addCustomer(customer, bindingResult);
//				return "redirect:/";
//			}
		
//	@PostMapping("/customerManagement")
//	public String inscription(@Validated Customer customer, BindingResult bindingResult, Model model ) {
//		
//	
//			customerService.addCustomer(customer, bindingResult);
//			return "redirect:/";
//		}
		
	
	}
}
	
