package fr.spear.revision.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.spear.revision.Bean.User;
import fr.spear.revision.Bean.UserLogin;
import fr.spear.revision.repository.UserRepo;

@Service
public class UserLoginDetailService implements UserDetailsService{

	@Autowired
	public UserRepo userRepository;

	
	//CETTE METHODE DEFINIT QUE LON SE CONNECTE AVEC SON EMAIL     ICI CEST LE USERNAME QUI FAIT OFFICE DE EMAIL
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found !");
			
		}
		return new UserLogin(user);
		
	}
}
