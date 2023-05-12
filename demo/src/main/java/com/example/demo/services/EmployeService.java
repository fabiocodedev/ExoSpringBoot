package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.bean.Employe;
import com.example.demo.repository.EmployeRepository;

@Service
public class EmployeService {
	
	@Autowired
	private EmployeRepository employeRepository ;
	

	
	public String addEmploye(Employe employe, BindingResult bindingResult) {
		
		if (employeRepository.findByEmail (employe.getEmail ()) != null ) {
			bindingResult.addError (new FieldError ("employe"," email","Le mail existe deja...."));
			return ("/employeManagement");
		}
		else {
	        employeRepository.save(employe);
	        System.out.println("ELSE DU SERVICE");
	        return ("/");
		}
	}
	
	
	public Employe getEmployeByEmail(String email) {
			
			return employeRepository.findByEmail(email);
			
		}
	
	
	
	
	
	
	
	
	public List <Employe> listEmploye() {
		return employeRepository.findAll();
	}
	
	public Optional<Employe> findById(int id) {
		return employeRepository.findById(id);
	}
	
	public void deleteEmploye(Employe user) {
		employeRepository.delete(user);
	}

}
