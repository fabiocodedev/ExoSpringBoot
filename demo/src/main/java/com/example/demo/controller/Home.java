package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.bean.User;
import com.example.demo.services.UserService;

@Controller
//@ResponseBody
public class Home {
	
	@Autowired
	private UserService userService;


	@GetMapping("/")
		private String getList(Model model) {
			
			model.addAttribute("users", userService.listUsers());
			
			return "home";
			
		}
	
//	******************************** UPDATE ***********************************
	
	@GetMapping("/update/{identifiant}")
    public String getUserToUpdate(@PathVariable(value = "identifiant") int id, Model model){
        Optional<User> userFound = userService.findById(id);
       
        if(userFound.isPresent()) {
        	model.addAttribute("user",userFound.get());
        
        } 
        return "userManagement";
    }
	
//	************************************* DELETE METHODE 1 LONGUE *********************
	
//	@GetMapping("/delete/{identif}")
//	private String deleteUser(@PathVariable("identif")int id) {
//		User userToDelete =null;
//		Optional<User> userFound = userService.findById(id);
//		if (userFound.isPresent()) {
//			userToDelete = userFound.get();
//			userService.deleteUser(userToDelete);
//		}
//		return "redirect:/";
//	}
	
//	************************************* DELETE METHODE 2 BEST *********************
	
	@GetMapping("/delete/{identif}")
	private String deleteUser(@PathVariable("identif")int id) {
		Optional<User> userFound = userService.findById(id);
		if (userFound.isPresent()) {
			userService.deleteUser(userFound.get());
		}
		return "redirect:/";
	}
////	************************************* DELETE METHODE 3 PLUS COURT ! *********************
//	
//	@GetMapping("/delete/{identif}")
//	private String deleteUser(@PathVariable("identif")int id) {
//		if (userService.findById(id).isPresent()) {
//			userService.deleteUser(userService.findById(id).get());
//		}
//		return "redirect:/";
//	}
	
//	*************************** ARCHIVE ***************************
	
//	******** methode pour afficher la liste avec le form sur la mm page ***********
//	public void getEmployeesList(Model model) {
//        if (employeeService.read().size() > 0) {
//            model.addAttribute("employeesList", employeeService.read());
//        }
//    }
	
	

}
	

