package com.finartz.hrtaskapp.Services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.finartz.hrtaskapp.Entity.User;

public interface UserService {
	
	Page<User> getAllUsers(Pageable pageable);
	User getUser(Integer id);
	User getUserByName(String name);
	User addUser(User user);
	String findLoggedInUsername();
}
