package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bean.Employe;
import com.example.demo.services.EmployeService;

import jakarta.validation.Valid;


@Controller
public class EmployeController {
	
	@Autowired
	private EmployeService employeService;
	
	@GetMapping("/employeManagement")
	public String employeManagement(Employe employe, Model model) {
		model.addAttribute("employe", employe);
		return "employeManagement";
	}
	
	

	
//************************ CREATE EMPLOYE ********************************
	
	@PostMapping("/employeManagement")
	public String createEmploye(@Valid Employe employe, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "employeManagement";
			
		}else {
//			userRepository.save(user);
			employeService.addEmploye(employe);
			System.out.println(employe.getNom());
		}
		//redirect = redirige vers l'url donc /.../... ou / ou /..
		return "redirect:/";
		
	}
	

	
	
}
