package com.finartz.hrtaskapp.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.finartz.hrtaskapp.model.entity.User;

public interface UserService {
	
	Page<User> getAllUsers(Pageable pageable);
	User getUser(Integer id);
	List<User> getUserByName(String name);
	void addUser(User user);
	String findLoggedInUsername();
}
