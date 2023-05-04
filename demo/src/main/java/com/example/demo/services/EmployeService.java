package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Employe;
import com.example.demo.repository.EmployesRepository;

@Service
public class EmployeService {
	
	@Autowired
	private EmployesRepository employeRepository ;
	
	public void addEmploye(Employe user) {
		employeRepository.save(user);
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
