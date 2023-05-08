package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bean.Product;
import com.example.demo.bean.User;
import com.example.demo.services.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService ;

	@GetMapping("/add-product")
	public String addProduct(Product product, Model model) {
		model.addAttribute("product", product);
		return "addProduct";
	}

	
	//************************ CREATE PRODUCT ********************************
	
		
		@PostMapping("/add-product")
		public String createProduct(@Validated Product product, BindingResult bindingResult, User user) {
			
			if (bindingResult.hasErrors()) {
				System.out.println("Aucun produit créé...");
				return "user/login";
				
			}else {
				productService.addProduct(product, bindingResult, user);
				System.out.println(product.getTitre() + " a été créé !");
			}
			//redirect = redirige vers l'url donc /.../... ou / ou /..
//			return "redirect:/addProduct";
			return "addProduct";
			
		}
}
