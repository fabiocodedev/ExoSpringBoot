package com.example.demo.controller;

import java.util.Optional;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bean.Employe;
import com.example.demo.services.EmployeService;

import jakarta.validation.Valid;

@Controller
//@ResponseBody
public class HomeController {
	
	@Autowired
	private EmployeService employeService;


	@GetMapping("/")
		private String getList(Model model) {
			
			model.addAttribute("employe", employeService.listEmploye());
			
			return "home";
			
		}
	
//	******************************** UPDATE ***********************************
	// A METTRE LA OU LA METHODE EST INITIER 
	
	@GetMapping("/update/{identifiant}")
    public String getEmployeToUpdate(@PathVariable(value = "identifiant") int id, Model model){
        Optional<Employe> employeFound = employeService.findById(id);
       
        if(employeFound.isPresent()) {
        	model.addAttribute("employe",employeFound.get());
        
        } 
        return "employeManagement";
    }
	
	@PostMapping("/update/{aze}")
	public String EmployeUpdate(@PathVariable(value = "aze") int id, @Valid Employe employeDetail)
			throws AttributeNotFoundException{
		
		Employe employe = employeService.findById(id).orElseThrow(()-> new AttributeNotFoundException("Aucun user avec l'id :: " + id));
		
		employe.setNom(employeDetail.getNom());
		employe.setPrenom(employeDetail.getPrenom());
		employe.setEmail(employeDetail.getEmail());
		
		employeService.addEmploye(employe);
		
		
		return "redirect:/";
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
		Optional<Employe> userFound = employeService.findById(id);
		if (userFound.isPresent()) {
			employeService.deleteEmploye(userFound.get());
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
	

