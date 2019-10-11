package com.finartz.hrtaskapp.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled=true,jsr250Enabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private TheRestAuthenticationEntryPoint authEntryPoint;
	
	@Autowired 
	private UserDetailsService customUserDetailsService;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	        throws Exception
	    {	//custom user detail service ile değiştirilecek
	    	auth.userDetailsService(userDetailsService())
	    	.passwordEncoder(passwordEncoder());
	    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers()
		.antMatchers("/users/**","/tasks/**")
		.and()
		.authorizeRequests()
		.antMatchers("/login","/sign-up").permitAll()
		.antMatchers("/users/add").hasRole("ADMIN")
		.antMatchers("/users/**").hasRole("USER")
		.and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.NEVER)
        .and()
        .csrf().disable()
        .httpBasic()
        .authenticationEntryPoint(authEntryPoint);
		
	}
	
}
