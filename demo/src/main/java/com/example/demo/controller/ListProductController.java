package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.services.ProductService;

@Controller
public class ListProductController {
	
	@Autowired
	private ProductService productService;


	@GetMapping("/list-product")
		private String listproduct(Model model) {
			
			model.addAttribute("product", productService.listProduct());
			
			return "listProduct";
			
		}
	
}
