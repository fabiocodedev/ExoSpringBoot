package fr.spear.revision.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import fr.spear.revision.service.UserLoginDetailService;



@Configuration
public class Security {
	

	
	//LES 3 PREMIER @BEAN FONT REFERENCES AU USERDETAILSERVICE (VIENS DU LOGIN IMPLEMENTER PAR LE STARTER SECURITY) ET AU BCRYPTE DU PWD, SI PAS DE BCRYPTE, PAS DE 3 @BEAN
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserLoginDetailService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder()); 
        return authProvider;
    }
    

    //CETTE PARTIE PERMET DE CUSTOMIZER LA PAGE DE LOGIN ET LE FILTRAGE PAR PAGES
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
    .requestMatchers("").permitAll()
//    .requestMatchers("/login","/register").permitAll()
//    .requestMatchers("/add-product").hasAuthority("ADMIN")
//    .anyRequest().hasAnyAuthority("ADMIN","MEMBRE","USER")
    .anyRequest().authenticated() //-> ETRE AUTENTIFIER POUR ACCES A UNE PAGE
    .and()
    .formLogin()
    //POINTER LA PAGE DE LOGIN CUSTOM
    .loginPage("/login")
    //DEFINIT QUE L EMAIL EST LE USERNAME DE CONNECTION
    .usernameParameter("email")
    .defaultSuccessUrl("/", true)
    // true -> esquive le continue ou error dasn l'url
    .and()
    .logout()
    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    .logoutSuccessUrl("/login")
    .permitAll()
    .and() 
    .exceptionHandling().accessDeniedPage("/login");
    //.and() -> CHAQUE .and() CORRESPOND A UN BLOC DE FONCTION, AUTHORIZATION, LOGIN, LOGOUT, SESSION,....
    return http.build();
    }
	
}
