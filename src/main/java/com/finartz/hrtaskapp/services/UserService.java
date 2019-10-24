package com.finartz.hrtaskapp.services;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.finartz.hrtaskapp.model.dto.UserDto;
import com.finartz.hrtaskapp.model.entity.User;
import org.springframework.security.core.GrantedAuthority;

public interface UserService {
	
	Page<UserDto> getAllUsers(Integer page);
	Optional<User> getUser(Integer id);
	User getUserByUsername(String username);
	List<User> getUserByName(String name);
	User addUser(User user);
	String findLoggedInUsername();
	Collection<GrantedAuthority> findLoggedInRoles();
	boolean isAdmin();

}
