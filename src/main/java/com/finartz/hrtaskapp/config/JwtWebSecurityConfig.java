package com.finartz.hrtaskapp.config;

import com.finartz.hrtaskapp.controllers.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled=true,jsr250Enabled=true)
@Profile("jwt")
public class JwtWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationEntryPoint jwtAuthenticationEntrypoint;
    private UserDetailsService customUserDetailsService;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public JwtWebSecurityConfig(AuthenticationEntryPoint jwtAuthenticationEntrypoint, UserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.jwtAuthenticationEntrypoint = jwtAuthenticationEntrypoint;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/users/**","/tasks/**","/process/**","/metrics/**","/fail/**")
                .and()
                .authorizeRequests()
                .antMatchers("/authenticate","/h2-console/**").permitAll()
                .antMatchers("/users/add").hasRole("ADMIN")
                .antMatchers("/users/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/tasks/add").hasRole("ADMIN")
                .antMatchers("/tasks/**").hasRole("USER")
                .antMatchers("/process/add","/process/update/**","/process/delete/**").hasRole("ADMIN")
                .antMatchers("/process/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/metrics/**").hasRole("USER")
                .antMatchers("/fails/**").hasRole("USER")
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntrypoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
        ;
    }

}
