package com.finartz.hrtaskapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
	    {	
	    	auth.userDetailsService(customUserDetailsService)
	    	.passwordEncoder(passwordEncoder());
	    }

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers()
		.antMatchers("/users/**","/tasks/**")
		.and()
		.authorizeRequests()
		.antMatchers("/sign-up").permitAll() // unused mapping
		.antMatchers("/users/add").hasRole("ADMIN")
		.antMatchers("/users/**").hasAnyRole("ADMIN","USER")
		.antMatchers("/tasks/add").hasRole("ADMIN")
		.antMatchers("/tasks/**").hasRole("USER")
		.and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf().disable()
        .httpBasic()
        .authenticationEntryPoint(authEntryPoint);
	}
	
}
