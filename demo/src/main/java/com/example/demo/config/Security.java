package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.services.UserLoginDetailService;

@Configuration
public class Security {
	
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
    

    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
    .requestMatchers("/login","/","/userManagement" ).permitAll()
    .requestMatchers("/add-product").hasAuthority("ADMIN")
    .anyRequest().hasAnyAuthority("ADMIN","MEMBRE","USER")
    .and()
    .formLogin()
    .loginPage("/login")
    .usernameParameter("email")
    .defaultSuccessUrl("/", true)
    .and()
    .logout()
    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    .logoutSuccessUrl("/")
    .permitAll()
    .and()
    .exceptionHandling().accessDeniedPage("/login");
    return http.build();
    }

}
