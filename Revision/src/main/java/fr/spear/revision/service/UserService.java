package fr.spear.revision.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.spear.revision.Bean.User;
import fr.spear.revision.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	//CRUD -- C - Debut
	
	public boolean createUser(User user) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        
		if(userRepo.save(user) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	
	//CRUD -- C - Fin
	
	
	
	
	
	
	
	
	
	//CRUD -- R - Debut
	//CRUD -- R - Fin
	
	//CRUD -- U - Debut
	//CRUD -- U - Fin
	
	//CRUD -- D - Debut
	//CRUD -- D - Fin
	
}
