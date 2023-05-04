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

    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exeption {
//    	auth.authenticationProvider(authenticationProvider());
//    }
    

    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
    .requestMatchers("/login").permitAll()
    .anyRequest().permitAll()
    .and()
    .formLogin()
    .loginPage("/login")
    .usernameParameter("email")
    .defaultSuccessUrl("/")
    .and()
    .logout()
    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    .logoutSuccessUrl("/")
    .permitAll();
    return http.build();
    }

}
