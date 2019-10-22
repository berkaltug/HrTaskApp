package com.finartz.hrtaskapp.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finartz.hrtaskapp.db.repository.UserRepository;
import com.finartz.hrtaskapp.model.entity.User;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
        	throw  new UsernameNotFoundException(username +" not found");
        }
        
        return new org.springframework.security.core.userdetails.User(
        		user.getUsername(),
        		user.getPassword(),
        		true,
        		accountNonExpired, 
                credentialsNonExpired, 
                accountNonLocked, 
        		getAuthorities(user));
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user)
    {
        String[] userRoles =user.getRoles()
        					.stream()
        					.map(role->role.getRole())
        					.toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(userRoles);
    }
	
	

}
