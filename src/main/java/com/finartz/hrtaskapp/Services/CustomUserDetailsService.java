package com.finartz.hrtaskapp.Services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finartz.hrtaskapp.Entity.User;
import com.finartz.hrtaskapp.Repository.UserRepository;


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
        
        org.springframework.security.core.userdetails.User authUser = new org.springframework.security.core.userdetails.User(
        		user.getUsername(),
        		user.getPassword(),
        		true,
        		accountNonExpired, 
                credentialsNonExpired, 
                accountNonLocked, 
        		getAuthorities(user));
        
        return authUser;

	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user)
    {
        String[] userRoles =user.getRoles()
        					.stream()
        					.map(role->role.getRole())
        					.toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
	
	

}
