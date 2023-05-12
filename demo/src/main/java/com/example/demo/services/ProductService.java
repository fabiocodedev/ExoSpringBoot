package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.bean.Product;
import com.example.demo.bean.User;
import com.example.demo.bean.UserLogin;
import com.example.demo.repository.ProductRepository;

import jakarta.validation.Valid;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository ;
	@Autowired
	private UserService userService ;
	
	//************************ CREATE PRODUCT ********************************
	
	
	public String addProduct(Product product, BindingResult bindingResult, User user) {
		
		String userProduct =  ((UserLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		
		  if (userProduct != null) {
              //Instancie un nouvel object user avec le mail de user connecté

              user =new User();

              user = userService.getUserByEmail(userProduct);
              product.setUser(user);
              productRepository.save(product);
              return "addProduct";
		  }

		
		if (productRepository.findByTitre (product.getTitre ()) != null ) {
			bindingResult.addError (new FieldError ("product"," titre","Cet article existe deja...."));
			return ("/addProduct");
		} else {
	        productRepository.save(product);
	        return ("/");
		}
	}
	
	
	
	//************************ READ PRODUCT ********************************
	
	public List <Product> listProduct() {
		return productRepository.findAll();
	}
	
	
	
	//************************ UPDATE PRODUCT ********************************
	
    public String checkProductPresent(@PathVariable(value = "id") int id, Model model){
        Optional<Product> productFound = productRepository.findById(id);
       
        if(productFound.isPresent()) {
        	model.addAttribute("product",productFound.get());
        
        } 
        return "addProduct";
    }
	
	public String ProductUpdate(@PathVariable(value = "id") int id, @Valid Product product)
			throws AttributeNotFoundException{
		
		product = productRepository.findById(id).orElseThrow(()-> new AttributeNotFoundException("Aucun produit avec l'id :: " + id));
		
		product.setTitre(product.getTitre());
		product.setDescription(product.getDescription());
		product.setPrix(product.getPrix());
		
		productRepository.save(product);
		
		
		return "redirect:/addProduct";
	}
	
	
	
	//************************ DELETE PRODUCT ********************************
	
			
			public String deleteProduct(int id) {
				Optional<Product> articleFound = productRepository.findById(id);
				if (articleFound.isPresent()) {
					productRepository.delete(articleFound.get());
				}
				return "redirect:/list-product";
			}
	
}
