package com.finartz.hrtaskapp.services;


import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.finartz.hrtaskapp.model.dto.UserDTO;
import com.finartz.hrtaskapp.model.entity.User;
import org.springframework.security.core.GrantedAuthority;

public interface UserService {
	
	Page<UserDTO> getAllUsers(Integer page);
	User getUser(Integer id);
	User getUserByUsername(String username);
	List<User> getUserByName(String name);
	User addUser(User user);
	String findLoggedInUsername();
	Collection<GrantedAuthority> findLoggedInRoles();
	Boolean isAdmin();
}
