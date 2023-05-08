package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.bean.Product;
import com.example.demo.bean.User;
import com.example.demo.bean.UserLogin;
import com.example.demo.repository.ProductRepository;

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

}
