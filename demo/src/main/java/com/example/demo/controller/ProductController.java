package com.example.demo.controller;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
			return "listProduct";
			
		}
		
		//************************ UPDATE PRODUCT ********************************
		
//		@GetMapping("/update-product/{id}")
//		public void updateProduct(@Validated @PathVariable(value = "id") Product product, BindingResult bindingResult, Model model, int id) throws AttributeNotFoundException {
//			
//			productService.checkProductPresent(id, model);
//			productService.ProductUpdate(id,product);
//			
//			
//		}
		
		@GetMapping("/update-product/{id}")
		public String checkProduct(Model model, @PathVariable(value = "id") int id) throws AttributeNotFoundException {
			
			productService.checkProductPresent(id, model);	
			return "addProduct";
			
		}
		@PostMapping("/update-product/{id}")
		public String updateProduct(@Validated @PathVariable(value = "id")  int id, Product product) throws AttributeNotFoundException {
			
			productService.ProductUpdate(id,product);
			return "redirect:/addProduct";
			
		}
		
		//************************ DELETE PRODUCT ********************************
		
		
		
		@GetMapping("/delete-product/{id}")
		private String deleteProd(@PathVariable("id")int id) {
			productService.deleteProduct(id);
			return "redirect:/list-product";
		}
		
		
		
}
