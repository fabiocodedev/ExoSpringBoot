package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.bean.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.ProductService;

@Controller
public class ListProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepository;


	@GetMapping("/list-product")
		private String listproduct(Model model) {
			
			model.addAttribute("product", productService.listProduct());
			
			return "listProduct";
	}
	
	@GetMapping("/solo-product/{identifiant}")
    public String getEmployeToUpdate(@PathVariable(value = "identifiant") int id, Model model){
        Optional<Product> productFound = productRepository.findById(id);
       
        if(productFound.isPresent()) {
        	model.addAttribute("product",productFound.get());
        
        } 
        return "soloProduct";
    }
	
}
